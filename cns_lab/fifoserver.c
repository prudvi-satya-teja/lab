#include<sys/stat.h>
#include<fcntl.h>
#include<stdlib.h>
#include<string.h>
main()
{
 int wrfd,rdfd,n,d,ret_val,count;
 char buf[50];
 /*create the first named pipe */
 ret_val=mkfifo("np1",0666);
 /*create the second named pipe */
 ret_val=mkfifo("np2",0666);
 /*open the first named pipe for reading*/
 rdfd=open("np1",O_RDONLY);
 /*open the second named pipe for writing*/
 wrfd=open("np2",O_WRONLY);
 /*read from the first pipe*/
 n=read(rdfd,buf,50);
 buf[n]='\0';//end of line
 printf("full duplex server:read from the pipe:%s\n",buf);
 
/*convert the string to upper class*/
 count=0;
 while(count<n)
 {
 buf[count]=toupper(buf[count]);
 count++;
 }
 /*write the convertor string back to second pipe*/
 
write(wrfd,buf,strlen(buf));
}