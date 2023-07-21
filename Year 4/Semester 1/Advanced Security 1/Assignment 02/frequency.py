
text: str = "UZQSOVUOHXMOPVGPOZPEVSGZWSZOPFPESXUDBMETSXAIZVUEPHZHMDZSHZOWSFPAPPDTSVPQUZWYMXUZUHSXEPYEPOPDZSZUFPOMBZWPFUPZHMDJUDTMOHMQ"
text = text.lower()

def frequency(txt, sign):
    counter: int = 0
    for s in txt:
        if s != sign:
            continue
        counter = counter + 1
    return counter

print(text)

print('letter','occurence','precent')

for s in 'abcdefghijklmnopqrstuvwxyz':
    howMany = frequency(text.lower(), s)
    percent = 100 * howMany / len(text)
    print('{0} - {1} - {2}%'.format(s.upper(), howMany, round(percent, 1)))