# 25 Common Mistakes Beginners Make When Learning Backend APIs and REST APIs

> A practical guide for Java/Spring/Spring Boot developers, from basic HTTP/API concepts to production-level backend and system concerns.

## Table of Contents

1. [What an API Actually Is](#1-what-an-api-actually-is)
2. [Confusing REST With HTTP](#2-confusing-rest-with-http)
3. [Using the Wrong HTTP Method](#3-using-the-wrong-http-method)
4. [Designing URLs Around Actions](#4-designing-urls-around-actions)
5. [Ignoring HTTP Status Codes](#5-ignoring-http-status-codes)
6. [Returning Inconsistent Response Shapes](#6-returning-inconsistent-response-shapes)
7. [Exposing Database Entities Directly](#7-exposing-database-entities-directly)
8. [Putting Business Logic in Controllers](#8-putting-business-logic-in-controllers)
9. [Skipping Request Validation](#9-skipping-request-validation)
10. [Handling Errors Poorly](#10-handling-errors-poorly)
11. [Using Exceptions as Normal Control Flow](#11-using-exceptions-as-normal-control-flow)
12. [Ignoring Pagination, Filtering, and Sorting](#12-ignoring-pagination-filtering-and-sorting)
13. [Building Unsafe Dynamic Queries](#13-building-unsafe-dynamic-queries)
14. [Misunderstanding Transactions](#14-misunderstanding-transactions)
15. [Ignoring Authentication vs Authorization](#15-ignoring-authentication-vs-authorization)
16. [Trusting Client-Supplied Data](#16-trusting-client-supplied-data)
17. [Returning Sensitive Information](#17-returning-sensitive-information)
18. [Ignoring Idempotency and Retries](#18-ignoring-idempotency-and-retries)
19. [Making APIs Too Chatty](#19-making-apis-too-chatty)
20. [Ignoring Database and N+1 Problems](#20-ignoring-database-and-n1-problems)
21. [Skipping API Versioning and Compatibility](#21-skipping-api-versioning-and-compatibility)
22. [Treating Logging as `System.out.println`](#22-treating-logging-as-systemoutprintln)
23. [Ignoring Timeouts, External Services, and Resilience](#23-ignoring-timeouts-external-services-and-resilience)
24. [Ignoring Caching and Concurrency](#24-ignoring-caching-and-concurrency)
25. [Forgetting Observability, Testing, and Production Readiness](#25-forgetting-observability-testing-and-production-readiness)

---

## Introduction

When someone first learns backend development, it is common to think:

> "I create a controller, write a few endpoints, connect a database, return JSON, and I have built a backend."

That is enough to make a demo.

It is **not** enough to build a reliable API.

A backend API sits between multiple systems:

```text
Frontend / Mobile / Other Services
             |
             v
        HTTP Request
             |
             v
       Spring Controller
             |
             v
       Business Service
             |
             v
       Repository / DB
             |
             v
        HTTP Response
```

As the application grows, additional concerns appear:

```text
Clients
  |
  v
API / Controllers
  |
  +--> Authentication / Authorization
  |
  +--> Validation
  |
  +--> Business Logic
  |
  +--> Database / Cache
  |
  +--> External Services
  |
  +--> Logging / Metrics / Tracing
  |
  +--> Error Handling
  |
  v
Production Infrastructure
```

This guide organizes 25 common mistakes in roughly the order a developer encounters them:

1. Understand HTTP and API fundamentals.
2. Design clean endpoints.
3. Structure Spring applications properly.
4. Validate and handle errors.
5. Deal with data access and transactions.
6. Add security.
7. Think about reliability and distributed systems.
8. Prepare the API for production.

The examples use Java with Spring Boot.

---

# 1. What an API Actually Is

One of the first mistakes is treating an API as simply "a URL that returns JSON."

An API is a **contract between software components**.

For example:

```http
GET /api/users/42
```

The contract may define:

- what the client sends,
- authentication requirements,
- what `42` means,
- what HTTP status codes are possible,
- what JSON structure is returned,
- what happens when the user does not exist,
- what errors look like.

A simple Spring controller:

```java
@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }
}
```

The important idea is that the controller is only one part of the API.

### Why beginners get this wrong

They often focus on implementation:

```text
"How do I write the endpoint?"
```

instead of the contract:

```text
"What should this endpoint mean?"
```

Before implementing an endpoint, think about:

```text
Resource
HTTP method
URL
Request
Response
Status codes
Errors
Authentication
Authorization
Validation
```

---

# 2. Confusing REST With HTTP

REST and HTTP are related, but they are not the same thing.

**HTTP** is a network protocol.

**REST** is an architectural style for designing networked applications.

For example:

```http
GET /api/products/10
```

uses HTTP.

Calling the API "RESTful" depends on whether the API follows sensible resource-oriented principles.

A beginner might create:

```http
POST /api/getProduct
POST /api/createProduct
POST /api/deleteProduct
POST /api/updateProduct
```

This can work technically, but it ignores many useful HTTP semantics.

A more resource-oriented design is:

```http
GET    /api/products/10
POST   /api/products
PUT    /api/products/10
PATCH  /api/products/10
DELETE /api/products/10
```

The HTTP method communicates the operation.

### Important distinction

Do not obsess over whether every API is "pure REST."

In real systems, APIs often use REST-like HTTP APIs with practical extensions.

The important thing is understanding:

- HTTP semantics,
- resource modeling,
- predictable contracts,
- consistency.

---

# 3. Using the Wrong HTTP Method

HTTP methods have meanings.

A useful starting point is:

| Method | Typical meaning |
|---|---|
| `GET` | Read |
| `POST` | Create or trigger an operation |
| `PUT` | Replace a resource |
| `PATCH` | Partially modify a resource |
| `DELETE` | Delete |

For example:

```http
GET /api/orders/123
```

means:

> Retrieve order 123.

Creating:

```http
POST /api/orders
Content-Type: application/json

{
  "productId": 10,
  "quantity": 2
}
```

Updating:

```http
PATCH /api/orders/123
Content-Type: application/json

{
  "quantity": 5
}
```

Deleting:

```http
DELETE /api/orders/123
```

### Common beginner mistake

Using:

```http
POST /api/deleteUser
```

for deletion.

Or:

```http
GET /api/createOrder
```

for creation.

The latter is particularly dangerous because GET requests may be retried, cached, prefetched, or triggered by crawlers.

### Spring example

```java
@GetMapping("/{id}")
public UserResponse find(@PathVariable Long id) {
    return userService.find(id);
}

@PostMapping
@ResponseStatus(HttpStatus.CREATED)
public UserResponse create(@RequestBody CreateUserRequest request) {
    return userService.create(request);
}

@PatchMapping("/{id}")
public UserResponse update(
        @PathVariable Long id,
        @RequestBody UpdateUserRequest request) {
    return userService.update(id, request);
}

@DeleteMapping("/{id}")
@ResponseStatus(HttpStatus.NO_CONTENT)
public void delete(@PathVariable Long id) {
    userService.delete(id);
}
```

---

# 4. Designing URLs Around Actions

A common beginner API looks like this:

```text
POST /api/createUser
POST /api/updateUser
POST /api/deleteUser
GET  /api/getUser
```

The URL is describing an action.

A cleaner resource-oriented design is:

```text
POST   /api/users
GET    /api/users
GET    /api/users/{id}
PATCH  /api/users/{id}
DELETE /api/users/{id}
```

The URL identifies the **resource**.

The HTTP method identifies the operation.

### Think in nouns

Prefer:

```text
/users
/orders
/products
/payments
```

over:

```text
/createUser
/getOrders
/deleteProduct
```

### Nested resources

Relationships can sometimes be represented as:

```http
GET /api/users/42/orders
```

meaning:

> Get orders belonging to user 42.

But do not make URLs excessively nested:

```text
/users/42/orders/10/items/3/discounts/5/...
```

If a resource can be addressed independently, a flatter URL may be better.

---

# 5. Ignoring HTTP Status Codes

Another common beginner mistake is returning:

```http
200 OK
```

for everything.

For example:

```java
@GetMapping("/{id}")
public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
    UserResponse user = service.find(id);

    if (user == null) {
        return ResponseEntity.ok(null);
    }

    return ResponseEntity.ok(user);
}
```

This loses useful information.

A better API could return:

```text
200 OK
```

when the resource exists.

```text
404 NOT FOUND
```

when it does not.

```text
400 BAD REQUEST
```

when the request is invalid.

```text
401 UNAUTHORIZED
```

when authentication is missing or invalid.

```text
403 FORBIDDEN
```

when the user is authenticated but not allowed.

```text
409 CONFLICT
```

when the request conflicts with current state.

```text
500 INTERNAL SERVER ERROR
```

for unexpected server failures.

### Spring example

```java
@GetMapping("/{id}")
public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
    return service.find(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
}
```

Or, preferably, let a global exception handler translate domain exceptions into appropriate responses.

---

# 6. Returning Inconsistent Response Shapes

Imagine one endpoint returns:

```json
{
  "id": 1,
  "name": "Alice"
}
```

but another returns:

```json
{
  "success": true,
  "data": {
    "id": 2,
    "name": "Bob"
  }
}
```

and an error returns:

```json
{
  "errorMessage": "Something went wrong"
}
```

Inconsistency makes client development harder.

Define predictable response contracts.

For example, an error format:

```json
{
  "status": 404,
  "code": "USER_NOT_FOUND",
  "message": "User does not exist",
  "path": "/api/users/42",
  "timestamp": "2026-08-15T10:30:00Z"
}
```

Spring Boot supports standardized error handling through mechanisms such as `ProblemDetail`.

Example:

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleUserNotFound(
            UserNotFoundException ex) {

        ProblemDetail problem =
                ProblemDetail.forStatus(HttpStatus.NOT_FOUND);

        problem.setTitle("User not found");
        problem.setDetail(ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(problem);
    }
}
```

The exact response format is a design decision, but **consistency is the important part**.

---

# 7. Exposing Database Entities Directly

This is one of the most important Spring mistakes.

Suppose you have:

```java
@Entity
public class User {

    @Id
    private Long id;

    private String username;

    private String password;

    private String email;
}
```

Then you return:

```java
@GetMapping("/{id}")
public User getUser(@PathVariable Long id) {
    return repository.findById(id).orElseThrow();
}
```

This couples your API directly to your database model.

Worse, you might accidentally return:

```json
{
  "id": 1,
  "username": "alice",
  "password": "secret",
  "email": "alice@example.com"
}
```

### Use DTOs

Define an API-specific response:

```java
public record UserResponse(
        Long id,
        String username,
        String email
) {}
```

Then map:

```java
public UserResponse toResponse(User user) {
    return new UserResponse(
            user.getId(),
            user.getUsername(),
            user.getEmail()
    );
}
```

Now your database model and API contract can evolve independently.

### DTO layers

A common structure is:

```text
CreateUserRequest
UpdateUserRequest
UserResponse
```

instead of using one `User` class everywhere.

---

# 8. Putting Business Logic in Controllers

A beginner controller often becomes this:

```java
@PostMapping
public UserResponse create(@RequestBody CreateUserRequest request) {

    if (request.email() == null) {
        throw new RuntimeException("Email required");
    }

    User user = new User();
    user.setEmail(request.email());

    if (repository.findByEmail(request.email()).isPresent()) {
        throw new RuntimeException("Email already exists");
    }

    repository.save(user);

    sendWelcomeEmail(user);

    return new UserResponse(
            user.getId(),
            user.getEmail()
    );
}
```

The controller is doing too much.

A better separation is:

```text
Controller
   |
   v
Service
   |
   +--> Repository
   |
   +--> Other services
```

Controller:

```java
@PostMapping
public UserResponse create(
        @Valid @RequestBody CreateUserRequest request) {

    return userService.create(request);
}
```

Service:

```java
@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public UserResponse create(CreateUserRequest request) {

        if (repository.existsByEmail(request.email())) {
            throw new DuplicateEmailException();
        }

        User user = new User();
        user.setEmail(request.email());

        User saved = repository.save(user);

        return new UserResponse(
                saved.getId(),
                saved.getEmail()
        );
    }
}
```

The controller should primarily deal with HTTP concerns.

The service should deal with business behavior.

---

# 9. Skipping Request Validation

Do not assume the client sends valid data.

A request such as:

```json
{
  "email": "",
  "age": -50
}
```

should not reach business logic unchecked.

Use Jakarta Bean Validation.

```java
public record CreateUserRequest(

        @NotBlank
        String username,

        @NotBlank
        @Email
        String email,

        @Min(18)
        Integer age

) {}
```

Then:

```java
@PostMapping
public UserResponse create(
        @Valid @RequestBody CreateUserRequest request) {

    return userService.create(request);
}
```

Validation can catch structural problems early.

Examples:

```java
@NotNull
@NotBlank
@Size
@Email
@Min
@Max
@Pattern
```

### Important distinction

Validation is not the same as business rules.

This:

```java
@Min(18)
Integer age;
```

is input validation.

This:

```text
A user cannot cancel an order that has already shipped.
```

is a business rule.

Keep those concepts separate.

---

# 10. Handling Errors Poorly

A beginner might do:

```java
try {
    return service.create(request);
} catch (Exception e) {
    return null;
}
```

This is dangerous.

It hides the actual failure and can turn a real server error into a confusing response.

Another common mistake:

```java
catch (Exception e) {
    throw new RuntimeException("Something went wrong");
}
```

without logging the original exception or preserving useful context.

Use centralized exception handling.

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<ProblemDetail> handleDuplicateEmail(
            DuplicateEmailException ex) {

        ProblemDetail problem =
                ProblemDetail.forStatus(HttpStatus.CONFLICT);

        problem.setTitle("Email already exists");
        problem.setDetail(ex.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(problem);
    }
}
```

A useful error architecture separates:

```text
Expected business error
        |
        v
Known HTTP response

Unexpected programming/infrastructure error
        |
        v
Generic HTTP response + detailed server-side logs
```

Never expose stack traces, SQL details, credentials, or internal infrastructure information to API clients.

---

# 11. Using Exceptions as Normal Control Flow

Exceptions are useful for exceptional situations.

But this:

```java
try {
    User user = repository.findById(id).orElseThrow();
    return user;
} catch (Exception e) {
    return null;
}
```

is not good design.

For expected absence, use a suitable API:

```java
Optional<User> user = repository.findById(id);
```

Then explicitly decide what absence means:

```java
return repository.findById(id)
        .map(this::toResponse)
        .orElseThrow(() ->
                new UserNotFoundException(id));
```

The exception here represents an application-level condition that should become a `404`.

### Avoid overly broad catches

Bad:

```java
catch (Exception e) {
    // ignore
}
```

Better:

```java
catch (SpecificException e) {
    // handle the known case
}
```

If you cannot recover meaningfully, allow the exception to propagate to centralized error handling.

---

# 12. Ignoring Pagination, Filtering, and Sorting

A beginner endpoint often starts like this:

```http
GET /api/products
```

and returns every product.

That might work with:

```text
20 products
```

It can become a disaster with:

```text
20,000,000 products
```

Use pagination.

For example:

```http
GET /api/products?page=0&size=20
```

with optional filters:

```http
GET /api/products?page=0&size=20&category=book
```

and sorting:

```http
GET /api/products?page=0&size=20&sort=price,asc
```

Spring Data supports `Pageable`:

```java
@GetMapping
public Page<ProductResponse> findProducts(Pageable pageable) {
    return productService.findProducts(pageable);
}
```

Repository:

```java
Page<Product> findByCategory(
        String category,
        Pageable pageable
);
```

### Think about limits

Do not blindly allow:

```text
size=100000000
```

Set reasonable server-side limits.

For example, an application might allow:

```text
default size = 20
maximum size = 100
```

---

# 13. Building Unsafe Dynamic Queries

One dangerous beginner pattern is concatenating user input into SQL.

Bad:

```java
String sql =
    "SELECT * FROM users WHERE name = '" + username + "'";
```

This can create SQL injection vulnerabilities.

Use parameterized queries or frameworks that bind parameters safely.

With Spring Data:

```java
Optional<User> findByUsername(String username);
```

With JPQL:

```java
@Query("""
       SELECT u
       FROM User u
       WHERE u.email = :email
       """)
Optional<User> findByEmail(@Param("email") String email);
```

For more complex dynamic filtering, use:

- Spring Data Specifications,
- Criteria API,
- QueryDSL,
- carefully constructed parameterized queries.

### Also remember

SQL injection is not the only injection concern.

Be careful with user-controlled values in:

- SQL,
- JPQL,
- shell commands,
- file paths,
- HTML,
- logs,
- JSON/XML processing,
- NoSQL queries.

Never assume:

> "It came from my frontend, so it is safe."

The frontend is controlled by the client.

---

# 14. Misunderstanding Transactions

Suppose creating an order involves:

```text
1. Create order
2. Reduce inventory
3. Create payment record
```

What if step 3 fails?

Without proper transaction boundaries, you could get:

```text
Order created
Inventory reduced
Payment failed
```

Now the database is in an unexpected state.

Spring provides transaction management:

```java
@Transactional
public void createOrder(CreateOrderRequest request) {

    Order order = createOrder(request);

    inventoryService.reserve(
            request.productId(),
            request.quantity()
    );

    paymentRepository.save(
            createPayment(order)
    );
}
```

If a suitable transaction-bound failure occurs, Spring can roll back the database changes.

### But transactions have boundaries

A database transaction does not automatically roll back an external HTTP call.

For example:

```text
Database transaction
    |
    +--> Save order
    |
    +--> Call Stripe/payment provider
```

If the external service succeeds but your database transaction fails, you have a distributed consistency problem.

That leads into more advanced patterns such as:

- outbox pattern,
- idempotency,
- retries,
- compensating actions,
- asynchronous messaging.

---

# 15. Ignoring Authentication vs Authorization

These concepts are different.

**Authentication:**

> Who are you?

**Authorization:**

> Are you allowed to do this?

For example:

```text
User logs in
      |
      v
Authentication
      |
      v
User identity = 42
      |
      v
Authorization
      |
      v
Can user 42 delete order 100?
```

A common mistake is:

```java
@DeleteMapping("/orders/{id}")
public void delete(@PathVariable Long id) {
    orderRepository.deleteById(id);
}
```

Even if the user is logged in, that does not mean they can delete any order.

You need an authorization rule.

For example:

```java
if (!order.getUserId().equals(currentUserId)) {
    throw new AccessDeniedException("Not allowed");
}
```

Or use Spring Security method authorization where appropriate.

Conceptually:

```text
401 = identity problem
403 = permission problem
```

Do not confuse the two.

---

# 16. Trusting Client-Supplied Data

Suppose the frontend sends:

```json
{
  "userId": 42,
  "price": 1.00,
  "role": "ADMIN"
}
```

A beginner might simply persist all of it.

That is unsafe.

The server should determine authoritative values.

For example, the server should usually determine:

```text
current user
product price
permissions
order ownership
created timestamp
status transitions
```

Instead of trusting:

```json
{
  "userId": 42,
  "price": 1.00,
  "role": "ADMIN"
}
```

the request might only contain:

```json
{
  "productId": 123,
  "quantity": 2
}
```

Then the server loads the product and determines:

```text
price = database price
user = authenticated user
permissions = server-side authorization
```

### General rule

The client can **request** something.

The server decides whether it is **valid and allowed**.

---

# 17. Returning Sensitive Information

Never return sensitive fields just because they exist in your entity.

Dangerous examples include:

```text
password hashes
access tokens
refresh tokens
private keys
internal permissions
security answers
database credentials
internal infrastructure details
```

Even password hashes should generally never be part of an API response.

Use response DTOs:

```java
public record UserResponse(
        Long id,
        String username,
        String email
) {}
```

instead of:

```java
public record UserResponse(
        Long id,
        String username,
        String passwordHash,
        String internalRole,
        String refreshToken
) {}
```

### Be careful with logging too

This is also bad:

```java
log.info("Login request: {}", request);
```

if `request` contains a password or token.

Sensitive information must be protected both in:

```text
HTTP responses
Logs
Exceptions
Metrics
Tracing
Database dumps
```

---

# 18. Ignoring Idempotency and Retries

Distributed systems fail.

A client sends:

```http
POST /api/payments
```

The server processes it.

But the network connection dies before the client receives the response.

The client does not know whether the payment succeeded.

It retries.

Now you could have:

```text
Payment #1 = $100
Payment #2 = $100
```

The user was charged twice.

This is why idempotency matters.

An idempotency key can be used:

```http
POST /api/payments
Idempotency-Key: 7f8e9d...
```

The server stores the result associated with the key.

If the same request arrives again:

```text
same key
   |
   v
return previous result
```

instead of performing the operation again.

### Idempotency varies by operation

Generally:

```text
GET     -> intended to be safe/read-only
PUT     -> designed to be idempotent
DELETE  -> generally intended to be idempotent
POST    -> often not inherently idempotent
```

Do not assume that retrying an operation is harmless.

---

# 19. Making APIs Too Chatty

Imagine a frontend displaying a profile page.

It calls:

```text
GET /users/42
GET /users/42/orders
GET /users/42/preferences
GET /users/42/address
GET /users/42/notifications
GET /users/42/statistics
```

That is six network requests.

Sometimes this is perfectly acceptable.

Sometimes it creates unnecessary latency.

The problem is often called a **chatty API**.

Possible solutions include:

### Option 1: Better resource endpoints

```http
GET /users/42/profile
```

### Option 2: Purpose-specific aggregation

```http
GET /dashboard
```

### Option 3: Client-controlled field selection

For some architectures:

```http
GET /users/42?fields=id,name,email
```

### Option 4: GraphQL or another query-oriented API

The right choice depends on the system.

Do not blindly create one endpoint for every tiny piece of information.

But also do not create giant endpoints returning the entire database.

The goal is a useful balance.

---

# 20. Ignoring Database and N+1 Problems

Your Java code may look efficient while the database is doing terrible work.

A classic example is the **N+1 query problem**.

Suppose:

```java
List<Order> orders = orderRepository.findAll();

for (Order order : orders) {
    System.out.println(order.getCustomer().getName());
}
```

Depending on JPA mappings and fetch behavior, you might accidentally execute:

```text
1 query -> load orders

N queries -> load each customer
```

For 1,000 orders:

```text
1 + 1000 = 1001 queries
```

This can destroy performance.

### Do not solve it blindly with EAGER everywhere

Changing every relationship to:

```java
FetchType.EAGER
```

can create a different problem: huge object graphs and excessive queries/data.

Instead, understand what data the endpoint actually needs.

Possible tools include:

- fetch joins,
- entity graphs,
- projections,
- carefully designed queries,
- batch fetching,
- pagination.

### Always inspect SQL

When performance matters, look at what the database actually receives.

Do not assume:

```text
One repository call = One SQL query
```

JPA can generate many queries.

---

# 21. Skipping API Versioning and Compatibility

Your API is a contract.

Suppose version 1 returns:

```json
{
  "name": "Alice",
  "email": "alice@example.com"
}
```

Then you change it to:

```json
{
  "fullName": "Alice"
}
```

Existing clients may break.

This is a compatibility problem.

Possible versioning strategies include:

```text
/api/v1/users
/api/v2/users
```

or header/media-type based strategies.

The exact strategy matters less than having a deliberate compatibility policy.

### Prefer additive changes when possible

Usually safer:

```text
Add a new optional field
```

than:

```text
Rename an existing field
Remove an existing field
Change its meaning
Change its type
```

For example, adding:

```json
{
  "name": "Alice",
  "email": "alice@example.com",
  "displayName": "Alice"
}
```

is often less disruptive than changing:

```text
name -> displayName
```

### Think about clients you do not control

Your API may be consumed by:

- mobile apps,
- web applications,
- partner companies,
- internal services,
- scheduled jobs,
- third-party integrations.

You cannot assume everyone upgrades immediately.

---

# 22. Treating Logging as `System.out.println`

A beginner might debug with:

```java
System.out.println("Creating order");
System.out.println(userId);
```

This does not scale well.

Use structured application logging:

```java
private static final Logger log =
        LoggerFactory.getLogger(OrderService.class);

log.info("Creating order for user {}", userId);
```

Useful logs should help answer:

```text
What happened?
When?
Where?
For which request?
For which entity?
Did it succeed or fail?
How long did it take?
```

### Do not log secrets

Never casually log:

```text
passwords
access tokens
credit card data
private keys
session secrets
```

### Log levels matter

Typical levels:

```text
TRACE
DEBUG
INFO
WARN
ERROR
```

Do not use ERROR for everything.

For example:

```java
log.info("Order created: {}", orderId);
```

versus:

```java
log.error("Payment provider failed for order {}", orderId, ex);
```

---

# 23. Ignoring Timeouts, External Services, and Resilience

Suppose your API calls:

```text
Your API
   |
   v
Shipping Provider
```

What happens if the shipping provider hangs for 60 seconds?

Your API thread/request may wait.

Now 100 requests arrive:

```text
100 requests
   |
   v
100 waiting calls
   |
   v
system becomes overloaded
```

Always think about:

- connection timeouts,
- read timeouts,
- connection pool limits,
- retries,
- circuit breakers,
- rate limits,
- fallback behavior.

### Be careful with retries

This is dangerous:

```text
POST payment
   |
   failure
   |
   retry
   |
   duplicate payment
```

Retries must be compatible with the operation's semantics.

### Example architecture

```text
API
 |
 +--> timeout
 |
 +--> retry only when appropriate
 |
 +--> circuit breaker
 |
 +--> fallback / failure handling
 |
 v
External service
```

Spring applications often use HTTP clients such as `WebClient` or `RestClient`, depending on the application's needs.

The exact client is less important than understanding that network calls can fail.

---

# 24. Ignoring Caching and Concurrency

Two advanced problems often appear together.

## Caching

Suppose:

```http
GET /api/products/123
```

is called 10,000 times per minute while the product changes once per hour.

Querying the database 10,000 times may be wasteful.

A cache can reduce repeated work:

```text
Request
  |
  v
Cache?
 /   \
yes   no
 |     |
 v     v
return DB
       |
       v
     cache
```

But caching introduces problems:

```text
How long should data live?
When is it invalidated?
Can stale data be returned?
What happens when multiple instances have different caches?
```

A cache is not automatically an improvement.

## Concurrency

Imagine two requests arrive at almost exactly the same time:

```text
Request A: read stock = 1
Request B: read stock = 1

Request A: buy item
Request B: buy item
```

Without appropriate concurrency control, both might succeed.

Possible solutions include:

- database constraints,
- optimistic locking,
- pessimistic locking,
- atomic database updates,
- carefully designed transactions.

For JPA, optimistic locking can use:

```java
@Version
private Long version;
```

This allows Hibernate/JPA to detect conflicting updates.

### Important lesson

Correctness under one request is not enough.

You must also think about:

```text
10 requests
100 requests
10,000 concurrent requests
multiple application instances
```

---

# 25. Forgetting Observability, Testing, and Production Readiness

The final beginner mistake is thinking:

> "It works on my machine, therefore the backend is finished."

Production systems need evidence that they work.

Think about three major observability signals:

```text
Logs
Metrics
Traces
```

## Logs

Tell you what happened.

```text
Order 123 created
Payment provider returned timeout
```

## Metrics

Tell you how the system behaves statistically.

Examples:

```text
HTTP request count
HTTP error rate
p50 latency
p95 latency
p99 latency
database connection usage
JVM memory
CPU usage
```

## Traces

Help follow a request across services:

```text
API
 |
 +--> User Service
 |
 +--> Order Service
 |      |
 |      +--> Database
 |
 +--> Payment Service
```

Without tracing, distributed failures can be extremely difficult to diagnose.

---

## Testing

At minimum, understand the difference between:

### Unit tests

Test a small piece of logic in isolation.

```java
@Test
void shouldRejectNegativeQuantity() {
    // test business logic
}
```

### Integration tests

Test multiple components together:

```text
Controller
   |
Service
   |
Repository
   |
Database
```

### API tests

Verify actual HTTP behavior:

```http
POST /api/users

Expected:
201 Created
```

### Security tests

Verify things such as:

```text
Unauthenticated user -> rejected
Authenticated normal user -> allowed where appropriate
Normal user -> forbidden from admin operation
Admin -> allowed
```

---

# A Practical Spring Boot Structure

A reasonable starting project structure is:

```text
src/main/java/com/example/app/

├── controller/
│   └── UserController.java
│
├── service/
│   └── UserService.java
│
├── repository/
│   └── UserRepository.java
│
├── entity/
│   └── User.java
│
├── dto/
│   ├── CreateUserRequest.java
│   ├── UpdateUserRequest.java
│   └── UserResponse.java
│
├── exception/
│   ├── UserNotFoundException.java
│   └── GlobalExceptionHandler.java
│
└── config/
    └── SecurityConfig.java
```

This is not a universal architecture.

As applications grow, teams may use:

```text
feature/
    user/
    order/
    payment/
```

or domain-driven structures.

The important thing is having clear boundaries.

---

# A Small End-to-End Example

Here is a simplified API demonstrating several of the principles above.

## Request DTO

```java
public record CreateUserRequest(

        @NotBlank
        String username,

        @NotBlank
        @Email
        String email

) {}
```

## Response DTO

```java
public record UserResponse(
        Long id,
        String username,
        String email
) {}
```

## Entity

```java
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    // getters and setters
}
```

## Repository

```java
public interface UserRepository
        extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);
}
```

## Service

```java
@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public UserResponse create(CreateUserRequest request) {

        if (repository.existsByEmail(request.email())) {
            throw new DuplicateEmailException(
                    request.email()
            );
        }

        User user = new User();
        user.setUsername(request.username());
        user.setEmail(request.email());

        User saved = repository.save(user);

        return new UserResponse(
                saved.getId(),
                saved.getUsername(),
                saved.getEmail()
        );
    }

    @Transactional(readOnly = true)
    public UserResponse getById(Long id) {

        User user = repository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException(id));

        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail()
        );
    }
}
```

## Controller

```java
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(
            @Valid @RequestBody CreateUserRequest request) {

        UserResponse response = service.create(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.getById(id)
        );
    }
}
```

## Exception Handler

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleNotFound(
            UserNotFoundException ex) {

        ProblemDetail problem =
                ProblemDetail.forStatus(HttpStatus.NOT_FOUND);

        problem.setTitle("User not found");
        problem.setDetail(ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(problem);
    }

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<ProblemDetail> handleDuplicate(
            DuplicateEmailException ex) {

        ProblemDetail problem =
                ProblemDetail.forStatus(HttpStatus.CONFLICT);

        problem.setTitle("Email already exists");
        problem.setDetail(ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(problem);
    }
}
```

This small example already demonstrates:

```text
HTTP method semantics
Resource-oriented URLs
DTOs
Validation
Controller/service separation
Repository abstraction
Transactions
HTTP status codes
Centralized error handling
Database uniqueness
```

It is still not a complete production system.

That is the point.

---

# A Mental Model for Learning Backend APIs

When designing an endpoint, ask these questions in order.

## 1. What resource am I working with?

Example:

```text
User
Order
Product
Payment
```

## 2. What operation is being performed?

```text
Read
Create
Update
Delete
Search
Trigger a domain operation
```

## 3. What HTTP method expresses it?

```text
GET
POST
PUT
PATCH
DELETE
```

## 4. What should the URL look like?

Example:

```http
GET /api/orders/123
```

## 5. What does the client control?

Example:

```json
{
  "productId": 10,
  "quantity": 2
}
```

## 6. What does the server control?

Example:

```text
user identity
price
permissions
order ownership
status
timestamps
```

## 7. What validation is required?

```text
Required fields
Format
Range
Length
Business rules
```

## 8. What can go wrong?

```text
Not found
Invalid input
Unauthorized
Forbidden
Conflict
Database failure
External service failure
Timeout
```

## 9. What status code represents each case?

```text
200
201
204
400
401
403
404
409
429
500
503
```

## 10. Can this operation be safely retried?

This is especially important for:

```text
Payments
Orders
Emails
Messages
External API calls
```

## 11. What happens under concurrency?

Ask:

```text
What if two requests happen simultaneously?
```

## 12. What happens at 10x the current traffic?

Ask:

```text
Will the database survive?
Will memory survive?
Will external services survive?
Will latency remain acceptable?
```

---

# Beginner-to-Intermediate Learning Path

If you are learning Spring Boot backend development, do not try to master all 25 topics at once.

A useful progression is:

```text
Stage 1: HTTP fundamentals
    |
    +--> HTTP methods
    +--> Status codes
    +--> Headers
    +--> JSON
    +--> Request/response lifecycle
    |
    v
Stage 2: Spring Boot API
    |
    +--> @RestController
    +--> @GetMapping
    +--> @PostMapping
    +--> @RequestBody
    +--> @PathVariable
    +--> @RequestParam
    |
    v
Stage 3: Application architecture
    |
    +--> Controller
    +--> Service
    +--> Repository
    +--> DTO
    +--> Entity
    |
    v
Stage 4: Data
    |
    +--> JPA
    +--> Transactions
    +--> SQL
    +--> Indexes
    +--> Pagination
    +--> N+1
    |
    v
Stage 5: Security
    |
    +--> Authentication
    +--> Authorization
    +--> Password hashing
    +--> Sessions/JWT
    +--> CORS/CSRF concepts
    |
    v
Stage 6: Reliability
    |
    +--> Timeouts
    +--> Retries
    +--> Idempotency
    +--> Transactions
    +--> Concurrency
    +--> Caching
    |
    v
Stage 7: Production
    |
    +--> Logging
    +--> Metrics
    +--> Tracing
    +--> Monitoring
    +--> Testing
    +--> Deployment
```

---

# The Most Important Lessons

If you remember only a few things, remember these:

### 1. An API is a contract

Do not think only about how to implement the endpoint.

Think about how clients will depend on it.

### 2. HTTP has semantics

Use methods and status codes intentionally.

```text
GET    = read
POST   = create/operation
PUT    = replace
PATCH  = partial update
DELETE = delete
```

### 3. Keep responsibilities separated

A useful starting point is:

```text
Controller -> HTTP
Service    -> business logic
Repository -> persistence
DTO        -> API contract
Entity     -> persistence model
```

### 4. Never trust the client

The client can send:

```text
wrong values
malicious values
outdated values
unexpected values
```

The server must validate and authorize.

### 5. Errors are part of the API

A good API does not only define:

```text
200 OK
```

It defines failure behavior too.

### 6. Database correctness matters

Understand:

```text
transactions
constraints
indexes
locking
N+1 queries
pagination
```

### 7. Distributed systems fail

Network calls can:

```text
timeout
fail
duplicate
return slowly
partially succeed
```

Design accordingly.

### 8. Production is different from localhost

A production API needs:

```text
security
observability
testing
timeouts
resource limits
reliable deployments
backups
monitoring
```

---

# Final Checklist

Before calling a Spring Boot API "finished," ask:

- [ ] Are the URLs resource-oriented and consistent?
- [ ] Are HTTP methods used correctly?
- [ ] Are status codes meaningful?
- [ ] Are request and response DTOs defined?
- [ ] Is input validated?
- [ ] Is business logic outside the controller?
- [ ] Are errors handled centrally?
- [ ] Are sensitive fields excluded from responses and logs?
- [ ] Is authentication implemented where needed?
- [ ] Is authorization checked for every protected operation?
- [ ] Does the server avoid trusting client-controlled fields?
- [ ] Are database queries safe from injection?
- [ ] Are transactions used where atomicity is required?
- [ ] Are list endpoints paginated?
- [ ] Have N+1 queries been considered?
- [ ] Are database constraints used for important invariants?
- [ ] Are external calls protected with sensible timeouts?
- [ ] Are retries safe for the operation?
- [ ] Is idempotency considered for retryable operations?
- [ ] Are API compatibility/versioning concerns understood?
- [ ] Is logging structured and useful?
- [ ] Are metrics and tracing available where needed?
- [ ] Are unit/integration/API/security tests present?
- [ ] Has concurrency been considered?
- [ ] Has the system been tested under realistic load?

---

# Closing Thought

The biggest transition in backend development is moving from:

> "I can make an API endpoint."

to:

> "I can design an API that remains understandable, secure, correct, and reliable when real clients, real data, failures, retries, concurrent requests, and changing requirements interact with it."

Spring Boot makes it relatively easy to create endpoints.

The harder—and more valuable—skill is understanding **what those endpoints should mean and how they behave when things go wrong**.

That is where backend engineering really begins.
