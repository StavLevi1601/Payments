package com.pay.payment.dto;

public class RetrieveTransactionResponseDTO {

    private String cardHolderName;
    private String pan;
    private String expiryDate;


    public String getCardHolderName() {
        return cardHolderName;
    }
    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }
    public String getPan() {
        return pan;
    }
    public void setPan(String pan) {
        this.pan = pan;
    }
    public String getExpiryDate() {
        return expiryDate;
    }
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }
    
    public String encryptPan(String pan)
    {
        int counter=0;
        String encryptionPan="";
        Character number;
        for(int i=0;i<pan.length();i++)
        {
            if ((Character.isDigit(pan.charAt(i))))
            {
                counter++;
            }
        }
        for(int j=0;j<counter;j++)
        {
            if (j<counter-4)
            {
                encryptionPan+="*";
            }
            else {
                number=pan.charAt(j);
                encryptionPan+=String.valueOf(number);
            }

        }
        return encryptionPan;
    }

    public String encryptionNameAndExpiryDate(String data)
    {
        int counter=0;
        String encryptionNameOrExpiryDate="";
        for(int i=0;i<data.length();i++)
        {
            if ((Character.isLetter(data.charAt(i))) || (Character.isDigit(data.charAt(i)) || (data.charAt(i)==' ')))
            {
                counter++;
            }
        }
        for(int i=0;i<counter;i++)
        {
            encryptionNameOrExpiryDate+="*";
        }
        return encryptionNameOrExpiryDate;
    }
    
    
}
