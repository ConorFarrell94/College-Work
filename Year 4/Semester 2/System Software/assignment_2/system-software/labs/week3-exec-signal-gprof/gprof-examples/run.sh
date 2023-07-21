gcc -Wall -pg gprofExample.c -o test
./test
gprof test gmon.out > report.txt
clear
cat report.txt
rm test gmon.out report.txt
