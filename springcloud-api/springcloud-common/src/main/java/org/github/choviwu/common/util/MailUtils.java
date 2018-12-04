package org.github..common.util;

/**
 * @author
 * @date 2018/6/21
 * Description :
 */

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

/**
 * 使用javamail发送邮件
 * 2018年3月10日
 */
public class MailUtils implements Serializable{

        String to ;// 收件人
        String from;// 发件人
        String host ;// smtp主机
        String username;
        String password;
        String filename = "";// 附件文件名
        String subject;// 邮件主题
        String content;// 邮件正文
        Vector file = new Vector();// 附件文件集合

        public static double a=1 ;


        public String getTo() {
            return to;
        }

        public String getFrom() {
            return from;
        }

        public String getHost() {
            return host;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getFilename() {
            return filename;
        }

        public void setFilename(String filename) {
            this.filename = filename;
        }

        public String getSubject() {
            return subject;
        }

        public String getContent() {
            return content;
        }

        public Vector getFile() {
            return file;
        }

        public void setFile(Vector file) {
            this.file = file;
        }

        public static double getA() {
            return a;
        }

        public static void setA(double a) {
            MailUtils.a = a;
        }

        /**
         * <br>
         * 方法说明：默认构造器 <br>
         * 输入参数： <br>
         * 返回类型：
         */
        public MailUtils() {
            //如果是ee项目，初始的时候可以把邮件存到库里
//            to= Global.FIELD.get("mail_recipient");
//            from= Global.FIELD.get("mail_sender");
//            host =Global.FIELD.get("mail_host");
//            username =Global.FIELD.get("mail_username");
//            password =Global.FIELD.get("mail_password");
//            subject =Global.FIELD.get("mail_subject");
//            content =Global.FIELD.get("mail_content");
        }

        /**
         * <br>
         * 方法说明：构造器，提供直接的参数传入 <br>
         * 输入参数： <br>
         * 返回类型：
         */
        public MailUtils(String to, String from, String smtpServer,
                         String username, String password, String subject, String content) {
            this.to = to;
            this.from = from;
            this.host = smtpServer;
            this.username = username;
            this.password = password;
            this.subject = subject;
            this.content = content;
        }

        /**
         * <br>
         * 方法说明：设置邮件服务器地址 <br>
         * 输入参数：String host 邮件服务器地址名称 <br>
         * 返回类型：
         */
        public void setHost(String host) {
            this.host = host;
        }

        /**
         * <br>
         * 方法说明：设置登录服务器校验密码 <br>
         * 输入参数： <br>
         * 返回类型：
         */
        public void setPassWord(String pwd) {
            this.password = pwd;
        }

        /**
         * <br>
         * 方法说明：设置登录服务器校验用户 <br>
         * 输入参数： <br>
         * 返回类型：
         */
        public void setUserName(String usn) {
            this.username = usn;
        }

        /**
         * <br>
         * 方法说明：设置邮件发送目的邮箱 <br>
         * 输入参数： <br>
         * 返回类型：
         */
        public void setTo(String to) {
            this.to = to;
        }

        /**
         * <br>
         * 方法说明：设置邮件发送源邮箱 <br>
         * 输入参数： <br>
         * 返回类型：
         */
        public void setFrom(String from) {
            this.from = from;
        }

        /**
         * <br>
         * 方法说明：设置邮件主题 <br>
         * 输入参数： <br>
         * 返回类型：
         */
        public void setSubject(String subject) {
            this.subject = subject;
        }

        /**
         * <br>
         * 方法说明：设置邮件内容 <br>
         * 输入参数： <br>
         * 返回类型：
         */
        public void setContent(String content) {
            this.content = content;
        }

        /**
         * <br>
         * 方法说明：把主题转换为中文 <br>
         * 输入参数：String strText <br>
         * 返回类型：
         */
        public String transferChinese(String strText) {
            try {
                strText = MimeUtility.encodeText(new String(strText.getBytes(),
                        "GB2312"), "GB2312", "B");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return strText;
        }

        /**
         * <br>
         * 方法说明：往附件组合中添加附件 <br>
         * 输入参数： <br>
         * 返回类型：
         */
        public void attachfile(String fname) {
            file.addElement(fname);
        }

        /**
         * <br>
         * 方法说明：发送邮件 <br>
         * 输入参数： <br>
         * 返回类型：boolean 成功为true，反之为false
         */
        public boolean sendMail() {

            // 构造mail session
            Properties props = new Properties() ;
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.auth", "true");
            Session session = Session.getDefaultInstance(props,
                    new Authenticator() {
                        @Override
                        public PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });

            try {
                // 构造MimeMessage 并设定基本的值
                MimeMessage msg = new MimeMessage(session);
                //MimeMessage msg = new MimeMessage();
                msg.setFrom(new InternetAddress(from));


                //msg.addRecipients(Message.RecipientType.TO, address); //这个只能是给一个人发送email
                msg.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(to)) ;
                //subject = transferChinese(subject);
                msg.setSubject(subject);

                // 构造Multipart
                Multipart mp = new MimeMultipart();

                // 向Multipart添加正文
                MimeBodyPart mbpContent = new MimeBodyPart();
                mbpContent.setContent(content, "text/html;charset=gb2312");

                // 向MimeMessage添加（Multipart代表正文）
                mp.addBodyPart(mbpContent);

                // 向Multipart添加附件
                Enumeration efile = file.elements();
                while (efile.hasMoreElements()) {

                    MimeBodyPart mbpFile = new MimeBodyPart();
                    filename = efile.nextElement().toString();
                    FileDataSource fds = new FileDataSource(filename);
                    mbpFile.setDataHandler(new DataHandler(fds));
                    String filename=  MimeUtility.encodeText(fds.getName());
                    //<span style="color: #ff0000;">//这个方法可以解决附件乱码问题。</span>
                    //String filename= new String(fds.getName().getBytes(),"gb2312");

                    mbpFile.setFileName(filename);
                    // 向MimeMessage添加（Multipart代表附件）
                    mp.addBodyPart(mbpFile);

                }

                file.removeAllElements();
                // 向Multipart添加MimeMessage
                msg.setContent(mp);
                msg.setSentDate(new Date());
                msg.saveChanges() ;
                // 发送邮件

                Transport transport = session.getTransport("smtp");
                transport.connect(host, username, password);
                transport.sendMessage(msg, msg.getAllRecipients());
                transport.close();
            } catch (Exception mex) {
                mex.printStackTrace();
//          Exception ex = null;
//          if ((ex = mex.getNextException()) != null) {
//              ex.printStackTrace();
//          }
                return false;
            }
            return true;
        }



        /**
         * <br>
         * 方法说明：主方法，用于测试 <br>
         * 输入参数： <br>
         * 返回类型：
         */
        public static void main(String[] args) {
            MailUtils mailUtils = new MailUtils();
            mailUtils.setFrom("xxxx@sina.cn");//发送方
            mailUtils.setHost("smtp.sina.cn");
            mailUtils.setUserName("137720626840@sina.cn");//用户名
            mailUtils.setPassWord("wuchaowei");//密码
            mailUtils.setSubject("Yeasasdsafdsfd");//发送Title
            mailUtils.setContent("Yeasasdsafdsfd");//内容  如果有文件内容mailUtils.attachfile("文件的url");
            mailUtils.setTo("@163.com");//接收方
            mailUtils.sendMail();
            System.out.println(MailUtils.a);
       /* if(MailUtils.a !=0 && a!=null){}*/

        }

}
