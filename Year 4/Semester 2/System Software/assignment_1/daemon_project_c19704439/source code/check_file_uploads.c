#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <dirent.h>
#include <sys/stat.h>

char **check_file_uploads(const char *dir_path, int *num_xml_files)
{
   DIR *dir;
   struct dirent *entry;
   char **xml_files = NULL;
   char *file_ext;

   // Open the directory
   dir = opendir(dir_path);
   if (dir == NULL)
   {
      fprintf(stderr, "Error opening directory %s\n", dir_path);
      exit(EXIT_FAILURE);
   }

   // Read each entry in the directory
   while ((entry = readdir(dir)) != NULL)
   {
      // Check if the entry is a file
      char file_path[1024];
      snprintf(file_path, sizeof(file_path), "%s/%s", dir_path, entry->d_name);
      struct stat st;
      if (stat(file_path, &st) == 0 && S_ISREG(st.st_mode))
      {
         // Get the file extension
         file_ext = strrchr(entry->d_name, '.');
         if (file_ext != NULL && strcmp(file_ext, ".xml") == 0)
         {
            // Add the file name to the array of XML files
            xml_files = realloc(xml_files, (*num_xml_files + 1) * sizeof(char *));
            if (xml_files == NULL)
            {
               fprintf(stderr, "Error: could not allocate memory for XML files\n");
               exit(EXIT_FAILURE);
            }
            xml_files[*num_xml_files] = strdup(entry->d_name);
            (*num_xml_files)++;
         }
      }
   }

   closedir(dir);

   // Return the array of XML files
   return xml_files;
}