"""
This file contains a small library of Python classes to help you
get started implementing a DNS resolver.
"""


class ByteArray:
    """
    A wrapper for a byte array. The library needs to keep track of our
    location in the array, so this class combines a list and in index
    to make this possible.
    """

    def __init__(self, array):
        """
        Construct a ByteArray object to encapsulate a given
        list of numbers.

        Parameters:
            array (list): The array you want the object to track
        """
        self.array = array
        self.index = 0
        self.saved_index = []

    def get(self):
        """
        Read the current byte and increment the index.

        Returns: The next byte in the array as an integer
        """
        if self.index >= len(self.array):
            return 0

        output = self.array[self.index]
        self.index += 1

        if output < 0:
            output += 256

        return output

    def set_index(self, index):
        """
        Set the index to a particular location.

        Parameters:
            index (int): The index you want to set
        """
        self.index = index

    def get_index(self):
        """
        Read the current index of the array without
        changing it.

        Returns: The current index of the array
        """
        return self.index

    def slice(self, n):
        """
        Take the next n bytes from the array, and return
        them in as a list. Increment the index by n.

        Parameters:
            n (int): The number of bytes you want to take

        Returns: The next n bytes in the array
        """
        n = (len(self.array) - self.index) if \
            n + self.index > len(self.array) else n
        output = []
        for i in range(n):
            output.append(self.array[self.index])
            self.index += 1
        return output

    def save_index(self):
        """
        Save the current index to an internal stack so that it
        can be restored later. This is used to allow us to jump
        around the array when reading compressed names, and then
        allowing us to jump back to our original location.
        """
        self.saved_index.append(self.index)

    def restore_index(self):
        """
        Restore the index from the stack placed there by save_index().
        Note this function does no error checking: be sure you call
        save_index() first so there is something to restore!
        """
        self.index = self.saved_index[-1]
        self.saved_index = self.saved_index[:-1]

    def read_short(self):
        """
        Read the next two bytes from the array as an int. Increment
        the index by 2.

        Returns: The next two bytes from the array as an int
        """
        output = self.get() & 0xFF
        output <<= 8
        return output + (self.get() & 0xFF)

    def read_int(self):
        """
        Read the next 4 bytes from the array as an int. Increment
        the index by 4.

        Returns: The next four bytes from the array as an int
        """
        output = 0

        for i in range(4):
            output <<= 8
            output += self.get()

        return output


class NameDecoder:
    """
    A class containing static methods to encode and decode DNS
    names. Includes full support for compressed names.
    """

    @staticmethod
    def encode(names):
        """
        Converts a list of strings to a DNS name in the form
        of a list of integers.

        Parameters:
            names (list): A list of strings representing the DNS name

        Returns: A list of integers containing the encoded data
        """
        output = []

        for s in names:
            output.append(len(s))
            output += s.encode("utf-8")

        output.append(0)
        return output

    @staticmethod
    def decode_byte_array(array):
        """
        Convert a ByteArray located at a DNS name to a
        list of strings containing the decoded name.

        Parameters:
            array (ByteArray): A ByteArray located at a DNS name

        Returns: A list of strings containing the decoded name
        """
        output = []
        length = array.get()

        # We finish when the length is zero, so keep going
        # while it is nonzero!
        while length != 0:

            # Deal with compressed labels
            if (length & 0xC0) != 0:
                b = array.get()
                offset = ((0x3F & length) << 8) + b
                array.save_index()
                array.set_index(offset)
                output.extend(NameDecoder.decode_byte_array(array))
                array.restore_index()
                return output

            # If not dealing with a compressed label, use ByteArray.slice()
            # to read the required number of characters and decode them
            # as a UTF-8 string.
            slice = array.slice(length)

            output.append(bytes(slice).decode("utf-8"))
            length = array.get()

        return output

    @staticmethod
    def decode(array):
        """
        Convert a list of ints containing a DNS name to a list of
        strings containing the decoded name.

        Parameters:
            array (list): A list of ints containing a DNS name

        Returns: A list of strings containing the decoded name
        """
        return NameDecoder.decode_byte_array(ByteArray(array))


class DNSQuestion:
    """
    This class represents a single DNS question. The name, qtype and qclass
    fields are public attributes in an object of this class.
    """

    def __init__(self, name, qtype, qclass, index=0):
        self.name = name
        self.qtype = qtype
        self.qclass = qclass
        self.index = index


def read_question(array):
    """
    Convert a ByteArray located at the start of a serialised DNS question into
    a DNSQuestion object.

    Parameters:
        array (ByteArray): A ByteArray located at the start of a serialised
                           DNS question

    Returns: A DNSQuestion object
    """
    index = array.get_index()
    name = NameDecoder.decode_byte_array(array)
    qtype = array.read_short()
    qclass = array.read_short()
    return DNSQuestion(name, qtype, qclass, index)


def write_question(question):
    """
    Serialise a DNSQuestion object into a list of integers

    Parameters:
        question (DNSQuestion): A DNSQuestion object

    Returns: A list of integers containing the serialised question
    """
    output = NameDecoder.encode(question.name)
    output.append((question.qtype & 0xFF00) >> 8)  # Upper byte of qtype
    output.append(question.qtype & 0xFF)  # Lower byte of qtype
    output.append((question.qclass & 0xFF00) >> 8)  # Upper byte of qclass
    output.append(question.qclass & 0xFF)  # Lower byte of qclass
    return output


class DNSAnswer:
    """
    This class represents a single DNS answer. The name, type, class,
    time-to-live, rdlength and rdata fields are public attributes of
    this class.
    """

    def __init__(self, name, type, class_, ttl, rdlength, rdata, index=0):
        self.name = name
        self.type = type

        # The underscore is to prevent a conflict
        # with Python's class keyword
        self.class_ = class_
        self.ttl = ttl
        self.rdlength = rdlength
        self.rdata = rdata
        self.index = index

    def cname_as_array_list(self, datagram):
        """
        If this answer is of type CNAME (i.e. if its type field is 5),
        convert the rdata into a list of strings.

        Parameters:
            datagram (DNSDatagram): The datagram containing this answer
                    (access to the global datagram is required to allow
                    for lookups of compressed names)

        Returns: A list of strings containing the DNS name of the CNAME,
                 or None if the type of this answer is not CNAME.
        """
        if self.type != 5 or datagram.bytes == None:
            return None

        datagram.bytes.save_index()
        datagram.bytes.set_index(self.index)
        output = NameDecoder.decode_byte_array(datagram.bytes)

        datagram.bytes.restore_index()
        return output


def read_answer(array):
    """
    Deserialise a ByteArray located at the start of a DNS answer
    into a DNSAnswer object.

    Parameters:
        array (ByteArray): A ByteArray located at the start of a
                           DNS answer

    Returns: A DNSAnswer object
    """
    name = NameDecoder.decode_byte_array(array)
    type = array.read_short()
    class_ = array.read_short()
    ttl = array.read_int()
    rdlength = array.read_short()
    index = array.get_index()
    rdata = array.slice(rdlength)
    return DNSAnswer(name, type, class_, ttl, rdlength, rdata, index)


def write_answer(answer):
    """
    Serialise a DNSAnswer object into the binary encoding of a DNS
    answer in a list of integers (one integer per byte).

    Parameters:
        answer (DNSAnswer): A DNSAnswer object to be serialised

    Returns: A list of integers containing the binary data
    """
    output = NameDecoder.encode(answer.name)
    output.append((answer.type & 0xFF00) >> 8)  # Upper byte of type
    output.append(answer.type & 0xFF)  # Lower byte of type
    output.append((answer.class_ & 0xFF00) >> 8)  # Upper byte of class
    output.append(answer.class_ & 0xFF)  # Lower byte of class
    output.append((answer.ttl & 0xFF000000) >> 24)  # MSB of ttl
    output.append((answer.ttl & 0xFF0000) >> 16)  # After the MSB of ttl
    output.append((answer.ttl & 0xFF00) >> 8)  # Before the LSB of ttl
    output.append(answer.ttl & 0xFF)  # LSB of TTL
    output.append((answer.rdlength & 0xFF00) >> 8)  # Upper byte of rdlength
    output.append(answer.rdlength & 0xFF)  # Lower byte of rdlength
    output.extend(answer.rdata)
    return output


class DNSHeader:
    """
    Class representing the header of a DNS datagram. This class contains
    attributes for the identifier, QR, opcode, AA, TC, RD, RA, Z, opcode,
    QDCOUNT, ANCOUNT, NSCOUNT and ARCOUNT fields.
    """

    def __init__(
        self,
        ident,
        qr,
        opcode,
        aa,
        tc,
        rd,
        ra,
        z,
        rcode,
        qdcount,
        ancount,
        nscount,
        arcount,
        index=0,
    ):
        self.ident = ident
        self.qr = qr
        self.opcode = opcode
        self.aa = aa
        self.tc = tc
        self.rd = rd
        self.ra = ra
        self.z = z
        self.rcode = rcode
        self.qdcount = qdcount
        self.ancount = ancount
        self.nscount = nscount
        self.arcount = arcount
        self.index = index


def read_header(array):
    """
    Deserialise a ByteArray located at the start of a DNS header
    into a DNSHeader object.

    Parameters:
        array (ByteArray): A ByteArray located at the start of a
               DNS header

    Returns: The deserialise DNSHeader object
    """
    ident = array.read_short()
    flags = array.read_short()
    qr = (flags & 0x8000) >> 15
    opcode = (flags & 0x7800) >> 11
    aa = (flags & 0x400) >> 10
    tc = (flags & 0x200) >> 9
    rd = (flags & 0x100) >> 8
    ra = (flags & 0x80) >> 7
    z = (flags & 0x70) >> 6
    rcode = flags & 0xF
    qdcount = array.read_short()
    ancount = array.read_short()
    nscount = array.read_short()
    arcount = array.read_short()
    return DNSHeader(
        ident, qr, opcode, aa, tc, rd, ra, z,
        rcode, qdcount, ancount, nscount, arcount
    )


def write_header(header):
    """
    Serialise a DNSHeader object into a byte stream in the form of
    a list of integers.

    Parameters:
        header (DNSHeader): A DNSHeader object

    Returns: A list of integers containing the serialised data
    """
    output = []
    output.append((header.ident & 0xFF00) >> 8)  # Upper byte of identifier
    output.append(header.ident & 0xFF)  # Lower byte of identifier
    flags = (
        (header.qr << 7)
        + (header.opcode << 3)
        + (header.aa << 2)
        + (header.tc << 1)
        + header.rd
    )
    output.append(flags)
    flags = (header.ra << 7) + (header.z << 4) + header.rcode
    output.append(flags)
    output.append((header.qdcount & 0xFF00) >> 8)  # Upper byte of qdcount
    output.append(header.qdcount & 0xFF)  # Lower byte of qdcount
    output.append((header.ancount & 0xFF00) >> 8)  # Upper byte of ancount
    output.append(header.ancount & 0xFF)  # Lower byte of ancount
    output.append((header.nscount & 0xFF00) >> 8)  # Upper byte of nscount
    output.append(header.nscount & 0xFF)  # Lower byte of nscount
    output.append((header.arcount & 0xFF00) >> 8)  # Upper byte of arcount
    output.append(header.arcount & 0xFF)  # Lower byte of arcount
    return output


class DNSDatagram:
    """
    A class representing a full DNS datagram. An instance contains
    a header, and a list of questions and answers. Authority and
    additional sections are not supported by this library, since
    they are not needed for DNS resolution.
    """

    def __init__(self, header, questions, answers, bytes=None):
        self.header = header
        self.questions = questions
        self.answers = answers
        self.bytes = bytes


def read_datagram_from_byte_array(array):
    """
    Deserialise a ByteArray located at a DNS datagram into a
    DNSDatagram object.

    Parameters:
        array (ByteArray): A ByteArray located at a DNS datagram

    Returns: A deserialised DNSDatagram object
    """
    header = read_header(array)
    questions = []
    answers = []

    for i in range(header.qdcount):
        questions.append(read_question(array))

    for i in range(header.ancount):
        answers.append(read_answer(array))

    return DNSDatagram(header, questions, answers, array)


def read_datagram(array):
    """
    Deserialise a list of bytes containing a DNS datagram into
    a DNSDatagram object.

    Parameters:
        array (list or bytes): A list of bytes containing a DNS datagram

    Returns: A deserialised DNSDatagram object
    """
    if type(array) == bytes: array = list(array)
    return read_datagram_from_byte_array(ByteArray(array))


def write_datagram(datagram):
    """
    Serialise a DNSDatagram object into a stream of bytes in the form of a
    list of integers.

    Parameters:
        datagram (DNSDatagram): A DNSDatagram object

    Returns: The serialised bytes in the form of a Python bytes object
    """
    # Start off by writing the header to the output list
    output = write_header(datagram.header)

    # Add each question (note: it is up to the caller to ensure that the
    # qdcount in the header and length of the datagram.questions array
    # match!)
    for q in datagram.questions:
        output.extend(write_question(q))

    # Add each answer (note: it is up to the caller to ensure that the
    # ancount in theheader and the length of the datagram.answers array
    # match!)
    for a in datagram.answers:
        output.extend(write_answer(a))

    return bytes(output)

