# EmailUtility

For using this jar:

include the context
in your application context.

2.Create the following bean in your application context for property file as in test folder:

classpath:mail.properties

3.Autowire the EmailUtils,create the email message as : EmailMessage message = new EmailMessage(sender, toList, ccList, bccList, subject, body, "text/html",filePath,fileNameForAttachment, mailTitle); 

4.For example refer the junit test.
