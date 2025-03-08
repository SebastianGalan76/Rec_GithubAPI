# Opis

Ta aplikacja to REST API zbudowane w Java + Quarkus 3, które pozwala na pobranie listy repozytoriów użytkownika GitHub (pomijając forki) oraz informacji o branchach i ostatnich commitach.

# Technologie
- Język: Java
- Framework: Quarkus 3
- API: https://developer.github.com/v3
- Testy: RestAssured

# Instalacja i uruchamianie
1. Klonowanie repozytorium
```
git clone https://github.com/SebastianGalan76/Rec_GithubAPI.git
cd Rec_GithubAPI
```

2. Uruchomienie aplikacji lokalnie
```
./mvnw quarkus:dev
```

# Endpointy
## Pobieranie repozytoriów użytkownika
```
GET /repos/{username}
```
### Przykładowa odpowiedź:
```
[
  {
    "name": "repository-name",
    "ownerLogin": "owner-login",
    "branches": [
      {
        "name": "main",
        "lastCommitSha": "123qwe"
      }
    ]
  }
]
```
### Obsługa błędów
- Jeśli użytkownik nie istnieje, API zwraca:
```
{
  "status": 404,
  "message": "User not found"
}
```
- Pozostałe błędy m.in. przekroczenie limitu zapytań do API
```
{
  "status": 404,
  "message": "error-message"
}
```
