# Payments
The gateway accepts credit card payments for processing and maintains a transaction history.
1. Payment rest api for processing and creating payment.
2. The application was developed on java,  springboot and maven.
3. In createPayment method we are getting json as an input, validate it and store it in AWS DynamoDB.
4. In retrieve transaction method we fetch the relevant record according the invoice, and return transaction as a json while mask the sensitive fields.
