
# Real-World Use Cases of `CompletableFuture` in Java

## ✅ 1. Parallel HTTP API Calls (e.g., in a Microservice)

You're building a backend service that:
- fetches **user profile** from one API
- fetches **user transactions** from another
- both can be done in **parallel**

```java
CompletableFuture<UserProfile> profileFuture = CompletableFuture.supplyAsync(() -> userService.fetchProfile(userId));
CompletableFuture<List<Transaction>> txnFuture = CompletableFuture.supplyAsync(() -> txnService.fetchTransactions(userId));

CompletableFuture<Void> combined = CompletableFuture.allOf(profileFuture, txnFuture);

combined.thenRun(() -> {
    UserProfile profile = profileFuture.join();
    List<Transaction> txns = txnFuture.join();
    System.out.println("User: " + profile.getName() + ", Txns: " + txns.size());
});
```

**Why use `CompletableFuture`?**
- Improves **performance** by doing things in parallel
- No need to **block** one after another
- Avoids callback hell with clear chaining

---

## ✅ 2. Asynchronous Database + Cache Logic

You're trying to fetch data from **cache**, and if not found, fetch from **database**.

```java
CompletableFuture<Optional<User>> userFuture = CompletableFuture.supplyAsync(() -> cacheService.getUser(userId))
    .thenCompose(cachedUser -> {
        if (cachedUser.isPresent()) {
            return CompletableFuture.completedFuture(cachedUser.get());
        } else {
            return CompletableFuture.supplyAsync(() -> dbService.getUser(userId));
        }
    });

userFuture.thenAccept(user -> System.out.println("Fetched user: " + user.getName()));
```

---

## ✅ 3. Background Processing After Response

Return response immediately and run something else asynchronously (e.g., logging, auditing):

```java
@RestController
public class MyController {

    @PostMapping("/process")
    public ResponseEntity<String> process(@RequestBody RequestData data) {
        CompletableFuture.runAsync(() -> auditService.logRequest(data));
        return ResponseEntity.ok("Request accepted");
    }
}
```

---

## ✅ 4. Retrying Tasks Asynchronously

Hit an unreliable third-party API with retries:

```java
public CompletableFuture<String> fetchWithRetry(String url, int attempts) {
    return CompletableFuture.supplyAsync(() -> httpService.call(url))
        .handle((result, ex) -> {
            if (ex != null && attempts > 1) {
                return fetchWithRetry(url, attempts - 1).join();
            } else if (ex != null) {
                throw new RuntimeException("Failed after retries", ex);
            }
            return result;
        });
}
```

---

## 🧵 Common Benefits in the Real World

| Benefit | Description |
|--------|-------------|
| 🔄 **Non-blocking I/O** | Ideal for IO-heavy tasks (API calls, DB, file ops) |
| ⚡ **Parallelism** | Run independent tasks in parallel and combine |
| 🔗 **Chaining** | Clean logic flow with `thenX()` methods |
| 🧩 **Composable** | Build workflows using `thenCombine`, `allOf`, etc. |
