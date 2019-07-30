# immutable-queue
#### Immutable object
- An immutable object is an object whose internal state remains constant after it has been entirely created.
- Need to use final when declaring its attributes. This guarantees that once the field is created, no one can change it.
- Create a defensive copy and return the copy instead the original one.
#### What we need to do
- Create the immutable stack
- Use the two immutable stack to implement immutable queue
#### How to execute
- mvn install
#### How to test
- mvn test