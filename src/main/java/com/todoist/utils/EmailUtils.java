package com.todoist.utils;

import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.search.SearchTerm;
import javax.mail.search.SubjectTerm;
import java.util.Properties;

public class EmailUtils {

    String login, pass;

    /**
     * EmailUtils.class can be used with gmail email only for now
     *
     * @param emailLogin    - gmail, like login@gmail.com
     * @param emailPassword - password to mail
     */

    public EmailUtils(String emailLogin, String emailPassword) {
        this.login = emailLogin;
        this.pass = emailPassword;
    }

    private Message[] getMailBySubject(String subject) throws Exception {
        Properties props = System.getProperties();
        props.setProperty("mail.imaps.host", "imap.gmail.com");
        props.setProperty("mail.imap.port", "993");
        props.setProperty("mail.store.protocol", "imaps");
        props.setProperty("mail.imap.connectiontimeout", "10000");
        props.setProperty("mail.imap.timeout", "10000");

        Session session = Session.getDefaultInstance(props);
        URLName urlName = new URLName("imaps://" + login.replace("@", "%40") + ":" + pass + "@imap.gmail.com");
        Store store = session.getStore(urlName);

        if (!store.isConnected())
            store.connect();

        SearchTerm searchTerm = new SubjectTerm(subject);
        Folder folder = store.getFolder("inbox");
        folder.open(Folder.READ_WRITE);
        return folder.search(searchTerm);
    }

    public String getFirstMailBySubject(String subject) throws Exception {
        int msgCount = 0;
        Message[] messages = getMailBySubject(subject);
        msgCount = messages.length;
        String context = saveParts(messages[msgCount - 1].getContent());
        context = subject + " " + context;
        return context;
    }

    public String getFirstMailByPartialSubject(String subject) throws Exception {
        int msgCount = 0;
        Message[] messages = getMailBySubject(".*" + subject + ".*");
        msgCount = messages.length;
        String context = saveParts(messages[msgCount - 1].getContent());
        context = subject + " " + context;
        return context;
    }

    private String saveParts(Object content) throws Exception {
        String msg = null;
        Multipart multi = (Multipart) content;
        int parts = multi.getCount();
        for (int j = 0; j < parts; ++j) {
            MimeBodyPart part = (MimeBodyPart) multi.getBodyPart(j);
            if (part.getContent() instanceof Multipart)
                msg = saveParts(part.getContent());
            else if (part.isMimeType("text/plain"))
                msg = part.getContent().toString();
        }
        return msg;
    }
}
