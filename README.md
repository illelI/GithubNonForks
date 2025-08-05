This application allows you to get user repositories that are not forks.

## Usage
You can run app from its directory using:
```
mvnw spring-boot:run
```
Then you can get non-forks by:
```
GET http://localhost:8080/api/v1/github/{user}/getNonForks
```

This will return response in format:
```
[
    {
        "repository": {repositoryName},
        "owner": {RepositoryOwner},
        "branches": [
            {
                "name": {branchName},
                "lastCommitSHA": {lastCommitSHA}
            }
        ]
    }
]
```
