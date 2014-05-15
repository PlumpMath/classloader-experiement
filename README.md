This is demo of how classloader working in JVM.

# build the jars
./sbt package

# Now, we are trying to call a method in called-0.1.jar from calling-0.1.jar through classloader.

# Case1) This is how Spark handel jars, and will cause NoClassDefFoundError
java -classpath calling/target/calling-0.1.jar Calling1 called/target/called-0.1.jar

# Case2) Load the method in called-0.1.jar using customLoader, and then construct the method using getDeclaredMethod will
# work. But it's not flexibly at all.
java -classpath calling/target/calling-0.1.jar Calling2 called/target/called-0.1.jar

# Case3) Working example.
java -classpath calling/target/calling-0.1.jar Calling3 called/target/called-0.1.jar