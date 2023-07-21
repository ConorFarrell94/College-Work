CC=gcc
objects = main.o areaOfRectangle.o areaOfSquare.o areaOfCircle.o area_of_triangle.o 
headers = areaOfRectangle.h areaOfSquare.h areaOfCircle.h area_of_triangle.h 

myprog : $(objects)
	$(CC) -o areaProg $(objects) -lm

main.o : main.c $(headers)
	$(CC) -c main.c

areaOfRectangle.o : areaOfRectangle.c
	$(CC) -c areaOfRectangle.c

areaOfSquare.o  :areaOfSquare.c areaOfRectangle.h
	$(CC) -c areaOfSquare.c

areaOfCircle.o : areaOfCircle.c
	$(CC) -c areaOfCircle.c

area_of_triangle.o : area_of_triangle.c
	$(CC) -c area_of_triangle.c -lm

clean:
	rm areaProg $(objects)

