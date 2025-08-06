# Changelog zmian – `## 2.0.0 (2025-07-17)`

> Info: 🔧 zmienione • ➕ dodane • ➖ usunięte

---

## 1. ksef-client

### 1.1 api.services

- **DefaultCertificateGenerator.java**: 🔧 metodę `generateSelfSignedCertificate` zastąpiono
  dwoma `generateSelfSignedCertificateRsa` i `generateSelfSignedCertificateEcdsa`
- **DefaultCryptographyService.java**: 🔧 wprowadzono zmiany w pobieraniu kluczy w konstruktorze; dodano dwie pomocnocze metody `parsePublicKeyFromPem`
  i `parsePrivateKeyFromPem`; dodano `encryptWithECDsaUsingPublicKey(byte[] content)` — domyślna metoda szyfrowania
  ECIES (ECDH + AES-GCM) na krzywej P-256.
- **DefaultKsefClient.java**: 🔧 zmieniono nazwę metody z `batchOpen` na `openBatchSession`; do metody `sendBatchParts`
  dodano nagłowek `Content-Type`; zmieniono sygnaturę metody `submitAuthTokenRequest` - dodano
  parametr `boolean verifyCertificateChain` i zmieniono `body` na `signedXml`; zmieniono nazwę metody `operations`
  na `permissionOperationStatus`; zmieniono nazwę metody `getInvoiceMetadane` na `queryInvoices`; dodano
  metody `getSessions`, `getActiveSessions`, `revokeCurrentSession`, `revokeSession`, `retrievePublicKeyCertificate`;
  dodano parametr `continuationToken` do
  metody `AuthenticationListResponse getActiveSessions(Integer pageSize, String continuationToken)`
  dodano parametr `continuationToken` do
  metody `SessionInvoicesResponse getSessionFailedInvoices(String referenceNumber, String continuationToken, Integer pageSize)`
- **DefaultQrCodeService.java**: ➕ nowa usługa do generowania QrCodes
- **DefaultVerificationLinkService.java**: ➕ nowa usługa generowania linków do weryfikacji faktury

### 1.2 api.client.interfaces

- **CertificateGenerator.java**: 🔧 zmiany zgodnie z implementacja w `DefaultCertificateGenerator.java`
- **CryptographyService.java**: 🔧 zmiany zgodnie z implementacja w `DefaultCryptographyService.java`
- **KSeFClient.java**: 🔧 dodanie opisów do metod; zmiany zgodnie z implementacja w `DefaultKsefClient.java`
- **QrCodeService.java**: ➕ nowy interfejs do generowania QRcodes zgodnie z implementacją w `DefaultQrCodeService.java`
- **VerificationLinkService.java**: ➕ nowy interfejs do tworzenia linków weryfikacyjnych do faktury zgodnie z
  implementacją w `DefaultVerificationLinkService.java`

### 1.3 api.client.model

- **AuthenticationChallengeResponse.java**: 🔧 zmiany typu pola `timestamp` z `OffsetDateTime` na `Instant`
- **EntityAuthorizationGrant.java**: 🔧 dodanie pola `String id` i zmiana typu pola `authorizationScope` z `String`
  na `EntityAuthorizationScope`
- **EuEntityPermission.java**: 🔧 dodanie pola `String id`, zmiana pola `permissionType` na `permissionScope`
- **PersonPermission.java**: 🔧 dodanie pola `String id`
- **SubunitPermission.java**: 🔧 dodanie pola `String id` i usunięcie pola `canDelegate`
- **QrCodeResult.java**: ➕ nowa klasa
- **ContextIdentifierType.java**: ➕ nowa klasa w pakiecie qrcode
- **AuthenticationListResponse.java**: ➕ nowa klasa
- **AuthenticationMethod.java**: ➕ nowa klasa
- **AuthenticationOperationStatusResponse.java**: ➕ nowa klasa
- **CommonSessionStatus.java**: ➕ nowa klasa
- **SessionInvoice.java**: 🔧 dodanie pola `String invoiceFileName`
- **SessionsQueryRequest.java**: ➕ nowa klasa
- **SessionsQueryResponse.java**: ➕ nowa klasa
- **SessionsQueryResponseItem.java**: ➕ nowa klasa
- **SessionType.java**: ➕ nowa klasa
- **ApiException.java**: 🔧 dodanie metody `getApiException`
- **EncryptionMethod.java**: ➕ nowy enum
- **PersonPermissionQueryType.java**: ➕ nowy enum
- **SystemCode.java**: ➕ nowy enum
- **EntityAuthorizationScope.java**: ➕ nowy enum
- **CertificateInfo.java**: 🔧 usunięcie pola `thumbprint`
- **PersonPermissionsQueryRequest**: 🔧 dodanie pola `PersonPermissionQueryType queryType`
-

### 1.4 api.builders

- **PersonPermissionsQueryRequestBuilder.java**: 🔧 dodanie pola `PersonPermissionQueryType queryType`
- **OpenBatchSessionRequestBuilder.java**: 🔧 z `withBatchFile` usunięcie parametru `boolean offlineMode` i wydzielenie
  do osobnej metody `withOfflineMode(boolean offlineMode)` oraz zmiana typu przyjmowanego argumentu
  metody `withFormCode` z `String` na `SystemCode`

---

## 2. demo-web-app

### 2.1 integrationTest

- Wspólne: 🔧 `Thread.Sleep` → `org.awaitility.Awaitility.await`;
- **EntityPermissionIntegrationTest.java**: 🔧 zmiany w scenariuszu testowym
- **EuEntityPermissionIntegrationTest.java**: 🔧 zmiany w scenariuszu testowym
- **EuEntityRepresentativeE2EPermissionTest.java**: 🔧 zmiany w scenariuszu testowym
- **IndirectPermissionIntegrationTest.java**: 🔧 zmiany w scenariuszu testowym
- **PersonPermissionIntegrationTest.java**: 🔧 zmiany w scenariuszu testowym
- **ProxyPermissionIntegrationTest.java**: 🔧 zmiany w scenariuszu testowym
- **SubUnitPermissionIntegrationTest.java**: 🔧 zmiany w scenariuszu testowym
- **OnlineSessionV2E2EIntegrationTest.java**: 🔧 zmiany w scenariuszu testowym oraz dodano testy end-to-end dla faktury w
  wersji 3

- ➖ usunięto `publicKey.pem` z resources
- ➕ dodano klasę testową `SessionIntegrationTest.java`
- ➕ dodano plik `invoice-template_v3.xml` zawierający przykładową fakturę w wersji 3
-
- **AuthorizationIntegrationTest.java**: dodano testy end-to-end dla tokenu w wariantach `ECDsa` i `Rsa`.

### 2.2 api

- ➕ dodano usługi w `ActiveSessionController.java` które wywołują bezpośrednio klienta ksef.
- **AuthController.java**: ➕ `POST auth-with-ksef-certificate`
- **QrCodeController.java**: ➕`POST /qr/certificate` ➕`GET /qr/invoice/ksef` ➕`GET /qr/invoice/offline`
- ➖ usunięto `publicKey.pem` z resources

### 2.3 test - api.services

- ➕ dodano `QrCodeTests.java`
- ➕ dodano `VerificationLinkServiceTests.java`

---

## 3. .http

- 🔧 do `auth.http` dodano wywołanie usługi `POST auth-with-ksef-certificate`
- ➕ w `qr-code.http` dodano wywołanie usług ➕`POST /qr/certificate` ➕`GET /qr/invoice/ksef` ➕`GET /qr/invoice/offline`
- 🔧 w `searchPermissions.http` zaktualizowano payload
- 🔧 w `session.http` dodano wywołanie usług ➕ `POST /session/query/` ➕ `POST /session/active/{pageSize}`
  ➕ `DELETE /session/revoke/current` ➕ `DELETE /session/revoke/{sessionReferenceNumber}`
- 🔧 w `subunit-subject-permission.http` zaktualizowano payload
- ➖ usunięto `scenario/BatchSession_E2E_WorksCorrectly.http`

---

## 4. Podsumowanie

| Typ zmiany   | Liczba plików |
|--------------|---------------|
| ➕ dodane     | 30            |
| 🔧 zmienione | 80            |
| ➖ usunięte   | 3             |

---
