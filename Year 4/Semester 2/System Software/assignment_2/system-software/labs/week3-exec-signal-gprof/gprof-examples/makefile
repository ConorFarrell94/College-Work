CC=gcc
CFLAGS=-Wall -pg

all: gprof_example

gprof_example: gprofExample2.c 
	$(CC) $(CFLAGS) -o $@ gprofExample2.c 
 
run: gprof_example
	./gprof_example
	gprof gprof_example gmon.out > report.txt

clean:
	rm gprof_example gmon.out report.txt