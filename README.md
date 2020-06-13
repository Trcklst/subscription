## Get subscription service

### Use

```
GET {host}/api/subscription/{userId}
```
```
POST {host}/api/subscription
Content-Type: application/json

{
   "userId": {Integer},
   "creditCard": {
     "number": {String},
     "expirationMonth": {Long},
     "expirationYear": {Long},
     "cvc": {String}
   },
   "subscriptionType": {SubscriptionType}
}
```