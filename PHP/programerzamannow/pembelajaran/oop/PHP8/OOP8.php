<?php

/**

========== LIST OF OOP FEATURES IN PHP 8 ==========
START OF THE LINE

# 1. Named Arguments
# 2. Attributes
# 3. Constructor Property Promotion
# 4. Union Types
# 5. Match Expression
# 6. Nullsafe Operators
# 7. String to Number Comparison
# 8. Consistent Type Error
# 9. Mixed Type V2
# 10. Non-Capturing Catches
# 11. Throw Expression
# 12. Allow ::class on Objects
# 13.
# 14.
# 15.

END OF THE LINE
===================================================
START OF SECTION 1

# 1. Named Arguments

@@ Look Up File NamedArguments.php

--> Named Arguments is a feature that allows you to pass arguments to a function by name instead of by position.


END OF SECTION 1
===================================================
START OF SECTION 2

# 2. Attributes
# Attributes Target

@@ Look Up File Attributes.php

--> Attributes is a feature that allows you to add metadata to a class or a method.
--> you can use Attributes in many things such as classes, functions, properties, and constants.


=======================
** Attributes Target **

@@ Look Up File Attributes.php

--> giving specific targets to the attribute

#[Attribute(Target::METHOD | Target::CLASS | Target::PROPERTY | Target::CONSTANT)]


END OF SECTION 2
===================================================
START OF SECTION 3

# 3. Constructor Property Promotion

@@ Look Up File ConstructorPropertyPromotion.php

--> a way to create constructor parameters from class properties without creating the properties, 
and assigning them inside the constructor.


END OF SECTION 3
===================================================
START OF SECTION 4

# 4. Union Types

@@ Look Up File UnionTypes.php

--> assign in two or more data types to a variable


END OF SECTION 4
===================================================
START OF SECTION 5

# 5. Match Expression
# Non-Equals

@@ Look Up File MatchExpression.php

--> a feature that allows you to use switch statements without the need for a separate if-else chain.
--> but remember use match expression with simple cases
--> if the cases itself complex such a having a func etc better use switch

================
** Non-Equals **

--> when you have a list of possible values and you want to use a switch statement to determine which case to execute.

END OF SECTION 5
===================================================
START OF SECTION 6

# 6. Nullsafe Operators

@@ Look Up File NullsafeOperators.php

--> Nullsafe Operators are operators that can be used to access properties and methods of a variable that may be null.

END OF SECTION 6
===================================================
START OF SECTION 7

# 7. String to Number Comparison

--> in PHP 8 there is change for string to number comparison

Comparison    | Before | After
0 == "0"      | true   | true
0 == "0.0"    | true   | true
0 == "foo"    | true   | false
0 == ""       | true   | false
42 == "   42" | true   | true
42 == "42foo" | true   | false

END OF SECTION 7
===================================================
START OF SECTION 8

# 8. Consistent Type Error

@@ Look Up File ConsistentTypeError.php

--> when making a funcction and sending an argument with wrong data type, it will trigger TypeError
--> unfortunately in PHP 7 it will not return TypeError just an error message/warning
--> in PHP 8 there is alot built-in function that when error it will throw TypeError when sending wrong data type

END OF SECTION 8
===================================================
START OF SECTION 9

# 9. Mixed Type V2

@@ Look Up File MixedType.php

END OF SECTION 9
===================================================
START OF SECTION 10

# 10. Non-Capturing Catches

@@ Look Up File NonCapturingCatches.php

--> in PHP 8 now you dont need to make variable exception if you dont plan to use it

END OF SECTION 10
===================================================
START OF SECTION 11

# 11. Throw Expression

@@ Look Up File ThrowExpression.php

--> in PHP 8 throw now is an expression not a statement
--> so now you cacn use it on for example ternary operator

END OF SECTION 11
===================================================
START OF SECTION 12

# 12. Allow ::class on Objects

@@ Look Up File ClassOnObjects.php

--> In PHP 7 to retrieve class name of an object you need to use ClassName::class or get_class($object)
--> In PHP 8 now you can use $object::class directly

END OF SECTION 12
===================================================
START OF SECTION 13

END OF SECTION 13
===================================================
START OF SECTION 14

END OF SECTION 14
===================================================
START OF SECTION 15

END OF SECTION 15
===================================================































*/