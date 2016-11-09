package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * Created by Andriana on 09.11.2016.
 */
class LoginCommand implements Command
{
//    private String card = "123456789012";
//    private String pin = "1234";
    private ResourceBundle validCreditCards = ResourceBundle.getBundle("verifiedCards");
    private Set<String> keys = validCreditCards.keySet();
    @Override
    public void execute() throws InterruptOperationException {
        String cardEntered;
        String pinEntered;
        while(true) {
            ConsoleHelper.writeMessage("Enter card number and pin");
            cardEntered = ConsoleHelper.readString().trim();
            pinEntered = ConsoleHelper.readString().trim();
            if (cardEntered.matches("\\d{12}") && pinEntered.matches("\\d{4}"))
            {
                for (String s : keys)
                {
                    if (cardEntered.equals(s))
                    {
                        if (pinEntered.equals(validCreditCards.getObject(s)))
                        {
                            ConsoleHelper.writeMessage("Success");
                            break;
                        }
                    } else
                    {
                        ConsoleHelper.writeMessage("Wrong data");
                        continue;
                    }
                }
            }
            else {
                ConsoleHelper.writeMessage("Invalid data");
                continue;
            }
        }
    }
}
