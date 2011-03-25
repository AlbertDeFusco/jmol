/* $RCSfile$
 * $Author$
 * $Date$
 * $Revision$
 *
 * Copyright (C) 2007  The Jmol Development Team
 *
 * Contact: jmol-developers@lists.sf.net
 *
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public
 *  License as published by the Free Software Foundation; either
 *  version 2.1 of the License, or (at your option) any later version.
 *
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this library; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 *  02110-1301, USA.
 */

package org.jmol.fah.checkxyz;

import java.io.File;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

/**
 * Sending mails.
 */
public class MailSender {

  private Configuration config;
  private String projectNum;
  private File[] projectFiles;
  private boolean testing;

  public MailSender(
      Configuration configuration,
      String project,
      File[] files,
      boolean test) {
    config = configuration;
    projectNum = project;
    projectFiles = files;
    testing = test;
  }

  /**
   * Send a mail.
   * 
   * @throws EmailException Exception when sending mail.
   */
  public void sendMail() throws EmailException {

    // Tests
    if ((config.getMailServer() == null) || ("".equals(config.getMailServer()))) {
      throw new IllegalStateException("Configuration is not complete: Mail server");
    }
    if ((config.getUserMail() == null) || ("".equals(config.getUserMail()))) {
      throw new IllegalStateException("Configuration is not complete: User mail");
    }
    if ((config.getUserName() == null) || ("".equals(config.getUserName()))) {
      throw new IllegalStateException("Configuration is not complete: User name");
    }

    // Create the mail
    MultiPartEmail email = new MultiPartEmail();
    email.setHostName(config.getMailServer());
    email.setSmtpPort(config.getMailPort());
    email.setSslSmtpPort("" + config.getMailSslPort());
    email.setSSL(config.getUseSsl());
    if (testing) {
      email.addTo(config.getUserMail(), config.getUserName());
    } else {
      email.addTo("nvervelle@gmail.com", "FoldingAtHome Files");
      email.addCc(config.getUserMail(), config.getUserName());
    }
    email.setFrom(config.getUserMail(), config.getUserName());
    email.setSubject("FoldingAtHome file(s) for project " + projectNum);
    email.setMsg(
        "This mail contains file(s) for project " + projectNum + ".\n" +
        "It has been submitted by " + config.getUserName());
    if ((config.getLogin() != null) && (!config.getLogin().equals("")) &&
        (config.getPassword() != null) && (!config.getPassword().equals(""))) {
      email.setAuthentication(
          config.getLogin(),
          config.getPassword());
    }

    // Create the attachments
    if (projectFiles != null) {
      for (int i = 0; i < projectFiles.length; i++) {
        if (projectFiles[i] != null) {
          EmailAttachment attachment = new EmailAttachment();
          attachment.setPath(projectFiles[i].getAbsolutePath());
          attachment.setDisposition(EmailAttachment.ATTACHMENT);
          attachment.setDescription("FoldingAtHome file for project " + projectNum);
          attachment.setName(projectFiles[i].getName());
          email.attach(attachment);
        }
      }
    }

    // Send
    email.send();
  }
}
