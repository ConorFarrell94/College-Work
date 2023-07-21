import string

def caesar_cipher(text, key, encrypt):
    if encrypt is True:
        key %= 26
        alphabet = string.ascii_uppercase
        # alphabet = string.ascii_lowercase
        keyed = alphabet[key:] + alphabet[:key]
        table = str.maketrans(alphabet, keyed)
        encrypted = text.translate(table)
        return encrypted

    elif encrypt is not True or key is None:
        for x in range(0, 26):
            key = 26 - x
            key %= 26
            print("----------------------------------------------------------")
            print("Key value of : ", key)
            alphabet = string.ascii_uppercase
            # alphabet = string.ascii_lowercase
            keyed = alphabet[key:] + alphabet[:key]
            table = str.maketrans(alphabet, keyed)
            decrypted = text.translate(table)
            print(decrypted)


def vinegere_cipher(plaintext, key, encrypt):
    ciphertext = plaintext
    key = key
    def pad_the_key(plaintext, key):
        padded_key = ''
        i = 0
        for char in plaintext:
            if char.isalpha():
                padded_key += key[i % len(key)]
                i += 1
            else:
                padded_key += ' '
        return padded_key

    def encrypt_decrypt_char(plaintext_char, key_char):
        if plaintext_char.isalpha():
            first_alpha_letter = 'a'
            if plaintext_char.isupper():
                first_alpha_letter = 'A'

            old_char_position = ord(plaintext_char) - ord(first_alpha_letter)
            key_char_position = ord(key_char.lower()) - ord('a')

            if encrypt is True:
                new_char_position = (old_char_position + key_char_position) % 26
            else:
                new_char_position = (old_char_position - key_char_position + 26) % 26
            return chr(new_char_position + ord(first_alpha_letter))
        return plaintext_char

    def vinegere_encrypt(plaintext, key):
        ciphertext = ''
        padded_key = pad_the_key(plaintext, key)
        for plaintext_char, key_char in zip(plaintext, padded_key):
            ciphertext += encrypt_decrypt_char(plaintext_char, key_char)
        return ciphertext

    def vinegere_decrypt(ciphertext, key):
        plaintext = ''
        padded_key = pad_the_key(ciphertext, key)
        for ciphertext_char, key_char in zip(ciphertext, padded_key):
            plaintext += encrypt_decrypt_char(ciphertext_char, key_char)
        return plaintext
    if encrypt == True:
        print(vinegere_encrypt(ciphertext, key))
    else:
        print(vinegere_decrypt(ciphertext, key))


def main():
    # for Caesar cipher :
    # if you want to encrypt you must format the args as such: caesarCipher(text, key, True)
    # if you want to decrypt you must format the args as such: caesarCipher(text, None, False)

    # for Vinegere cipher :
    # if you want to encrypt you must format the args as such: vigenere_cipher(text, key, True)
    # if you want to decrypt you must format the args as such: vigenere_cipher(text, key, False)

    p1 = 'RQH YDULDWLRQ WR WKH VWDQGDUG FDHVDU FLSKHU LV ZKHQ WKH DOSKDEHW LV "NHBHG" EB XVLQJ D ZRUG. LQ WKH WUDGLWLRQDO YDULHWB, RQH FRXOG ZULWH WKH DOSKDEHW RQ WZR VWULSV DQG MXVW PDWFK XS WKH VWULSV DIWHU VOLGLQJ WKH ERWWRP VWULS WR WKH OHIW RU ULJKW. WR HQFRGH, BRX ZRXOG ILQG D OHWWHU LQ WKH WRS URZ DQG VXEVWLWXWH LW IRU WKH OHWWHU LQ WKH ERWWRP URZ. IRU D NHBHG YHUVLRQ, RQH ZRXOG QRW XVH D VWDQGDUG DOSKDEHW, EXW ZRXOG ILUVW ZULWH D ZRUG (RPLWWLQJ GXSOLFDWHG OHWWHUV) DQG WKHQ ZULWH WKH UHPDLQLQJ OHWWHUV RI WKH DOSKDEHW. IRU WKH HADPSOH EHORZ, L XVHG D NHB RI "UXPNLQ.FRP" DQG BRX ZLOO VHH WKDW WKH SHULRG LV UHPRYHG EHFDXVH LW LV QRW D OHWWHU. BRX ZLOO DOVR QRWLFH WKH VHFRQG "P" LV QRW LQFOXGHG EHFDXVH WKHUH ZDV DQ P DOUHDGB DQG BRX FDQ W KDYH GXSOLFDWHV'
    p2 = 'FEV MRIZRKZFE KF KYV JKREURIU TRVJRI TZGYVI ZJ NYVE KYV RCGYRSVK ZJ "BVPVU" SP LJZEX R NFIU. ZE KYV KIRUZKZFERC MRIZVKP, FEV TFLCU NIZKV KYV RCGYRSVK FE KNF JKIZGJ REU ALJK DRKTY LG KYV JKIZGJ RWKVI JCZUZEX KYV SFKKFD JKIZG KF KYV CVWK FI IZXYK. KF VETFUV, PFL NFLCU WZEU R CVKKVI ZE KYV KFG IFN REU JLSJKZKLKV ZK WFI KYV CVKKVI ZE KYV SFKKFD IFN. WFI R BVPVU MVIJZFE, FEV NFLCU EFK LJV R JKREURIU RCGYRSVK, SLK NFLCU WZIJK NIZKV R NFIU (FDZKKZEX ULGCZTRKVU CVKKVIJ) REU KYVE NIZKV KYV IVDRZEZEX CVKKVIJ FW KYV RCGYRSVK. WFI KYV VORDGCV SVCFN, Z LJVU R BVP FW "ILDBZE.TFD" REU PFL NZCC JVV KYRK KYV GVIZFU ZJ IVDFMVU SVTRLJV ZK ZJ EFK R CVKKVI. PFL NZCC RCJF EFKZTV KYV JVTFEU "D" ZJ EFK ZETCLUVU SVTRLJV KYVIV NRJ RE D RCIVRUP REU PFL TRE K YRMV ULGCZTRKVJ.'
    print(caesar_cipher(p2, None, False))
    print("----------------------------------------------------------")
    print("VINEGERE CIPHER DECRYPTED")
    vigenere_text = 'XQKP IZ IMWEB LK AUVZCXKW PHL VPE RIKD ASOZZSBZI TOIE ESTD XEJWXM CPS-3. PHPA TA DPW NEZCWB YN S OIE-GPIB KGIPLBTBSWF, WNK UJ WGV KGEPV TA YVW KF APP NSDW NETITVSVY BIUIWQCBK (KUA WQ IX QFETPIW 64). QD A HNOIIMTI BGK LHBP NYZ EA TV IQNOKL PHL NTVKT VACPATWX, JMP I HU SWZQFC FVZ "YW KESND." PB D VYB LDAA BSM XMO DAZP QCXKLEOUA LZOV L WNF OZWN, QL O TOIE EO LGJ T YMLTVG FAEK WYM. GPWJ WL AEIBBWZ TOQD XBWUASZ JLKU QF 2006, ET SWZSOL SO IM EP EYCDZ BL VPMNQFC A UMH PKAZ BUUKEQYV KKOU. BSM CPS BATQWG (GPAYH PA CMKTDU PHZE WP BZA MK4 IYL WL5 XWMPTJ), EKA MJDLZ TVMZWWSPVR XBMKOUYM QZYU FAW AGAMC WX YRFXEIXIDUSPA. HM NQVJ T RVZE RWO HOUO EPO DSNIVCD ARI-2 NWRPIYBC EGQLK ZPUKQF OEJCCM. LCL ET Z 2012, IYL CPS-512 ES ZBTTV TGKKPVR OYWV.'
    key = "KISWAHILI"
    vinegere_cipher(vigenere_text, key, False)


main()
