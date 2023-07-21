// hello.c
#include <linux/module.h>
#include <linux/kernel.h>
#include <linux/init.h>

MODULE_LICENSE("GPL");
MODULE_AUTHOR("Author");
MODULE_DESCRIPTION("A Simple Hello World Module");

static int hello_init(void)
{
  printk(KERN_ALERT "Hello world\n");
  return 0;
}

static void hello_exit(void)
{
  printk(KERN_ALERT "Goodbye world\n");
}

module_init(hello_init);
module_exit(hello_exit);

