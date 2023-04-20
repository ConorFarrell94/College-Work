#ifndef DAEMON_TASKS
#define DAEMON_TASKS

#include <time.h>

void collect_reports(char **xml_files, int num_xml_files);

void backup_dashboard(void);

void lock_directories(void);

void unlock_directories(void);

char **check_file_uploads(const char *dir_path, int *num_xml_files);

void sig_handler(int);

void update_timer(struct tm *);

void log_changes(void);

void message_queue(void);

void manual_backup(void);

#endif
