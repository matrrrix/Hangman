word_bank = open("originalwordbank.txt", "r")

if word_bank.readable():
    startIndex = 0
    endIndex = 0
    count = 0
    words = []
    wordLine = word_bank.read()

    for word in wordLine:
        if word == " ":
            startIndex = count + 1
        if word == ",":
            endIndex = count
            words.append(wordLine[startIndex:endIndex])
        count += 1
    print(words)

word_bank.close()

word_banks = open("wordbank.txt", "w")

for word in words:
    word_banks.write(word + "\n")

word_banks.close()
