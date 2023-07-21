import ipaddress
import DNSStarter
import socket
import sys


# you can use this piece of code here to get whatever DNS server address youre connected to.
# example for line 26 - "dest = (dns_server, 53)"

# import dns.resolver
# dns_resolver = dns.resolver.Resolver()
# dns_server = dns_resolver.nameservers[0]

try:
    user_input = sys.argv[1].split(".")
except:
    print("error")
    exit()

# IPV4
try:
    print("\nIPV4 addresses : ")
    h = DNSStarter.DNSHeader(12345, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0)
    q = DNSStarter.DNSQuestion(user_input, 1, 1)
    d = DNSStarter.DNSDatagram(h, [q], [])
    b = DNSStarter.write_datagram(d)
    dest = ('10.140.52.27', 53)
    cxn = socket.socket(family=socket.AF_INET, type=socket.SOCK_DGRAM)
    cxn.sendto(b, dest)
    r = cxn.recvfrom(4096)[0]
    res = DNSStarter.read_datagram(r)
    val = 0
    for ipv4 in res.answers:
        if ipv4.type == 1:
            list = res.answers[val].rdata
            list_to_string = " ".join([str(elem) for elem in list])
            list_to_string = list_to_string.replace(" ", ".")
            print(ipaddress.ip_address(list_to_string))
        val += 1
except:
    print("error/none")
    pass

# IPV6
try:
    print("\nIPV6 addresses : ")
    h = DNSStarter.DNSHeader(12345, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0)
    q = DNSStarter.DNSQuestion(user_input, 28, 1)
    d = DNSStarter.DNSDatagram(h, [q], [])
    b = DNSStarter.write_datagram(d)
    dest = ('10.140.52.27', 53)
    cxn = socket.socket(family=socket.AF_INET, type=socket.SOCK_DGRAM)
    cxn.sendto(b, dest)
    r = cxn.recvfrom(4096)[0]
    res = DNSStarter.read_datagram(r)
    val = 0
    for ipv6 in res.answers:
        if ipv6.type == 28:
            print(ipaddress.ip_address(bytes(res.answers[val].rdata)))
            val += 1
except:
    print("error/none")
    pass

# CNAME
try:
    print("\nCNAME : ")
    h = DNSStarter.DNSHeader(12345, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0)
    q = DNSStarter.DNSQuestion(user_input, 5, 1)
    d = DNSStarter.DNSDatagram(h, [q], [])
    b = DNSStarter.write_datagram(d)
    dest = ('10.140.52.27', 53)
    cxn = socket.socket(family=socket.AF_INET, type=socket.SOCK_DGRAM)
    cxn.sendto(b, dest)
    r = cxn.recvfrom(4096)[0]
    res = DNSStarter.read_datagram(r)
    val = 0
    listing = []
    for ans in res.answers:
        cname = res.answers[val].cname_as_array_list(res)
    if cname != None:
        cname_str = " ".join([str(elem) for elem in cname])
        new_cname = cname_str.replace(" ", ".")
        listing.append(new_cname)
    val += 1
    print(', '.join(listing))
except:
    print("error/none")
    pass
