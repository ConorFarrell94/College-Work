CC = gcc
CFLAGS = -Wall -Werror -std=gnu99
OBJECTS = backup_dashboard.o check_file_uploads.o collect_reports.o daemon.o lock_directories.o sig_handler.o unlock_directories.o update_timer.o log_changes.o message_queue.o manual_backup.o

daemon: $(OBJECTS)
	$(CC) $(CFLAGS) -o daemon $(OBJECTS)
	rm -f $(OBJECTS)

backup_dashboard.o: backup_dashboard.c
	$(CC) $(CFLAGS) -c backup_dashboard.c

check_file_uploads.o: check_file_uploads.c
	$(CC) $(CFLAGS) -c check_file_uploads.c

collect_reports.o: collect_reports.c
	$(CC) $(CFLAGS) -c collect_reports.c

daemon.o: daemon.c
	$(CC) $(CFLAGS) -c daemon.c

lock_directories.o: lock_directories.c
	$(CC) $(CFLAGS) -c lock_directories.c

sig_handler.o: sig_handler.c
	$(CC) $(CFLAGS) -c sig_handler.c

unlock_directories.o: unlock_directories.c
	$(CC) $(CFLAGS) -c unlock_directories.c

update_timer.o: update_timer.c
	$(CC) $(CFLAGS) -c update_timer.c

log_changes.o: log_changes.c
	$(CC) $(CFLAGS) -c log_changes.c

message_queue.o: message_queue.c
	$(CC) $(CFLAGS) -c message_queue.c

manual_backup.o: manual_backup.c
	$(CC) $(CFLAGS) -c manual_backup.c

clean:
	rm -f $(OBJECTS) daemon
