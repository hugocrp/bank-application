# Exercice 7

Je vais répondre aux questions pour chaque commande Maven
## 1. mvn clean

### Phases exécutées :
- `clean`

### Fichiers/Dossiers dans target/ :
- Aucun (tout est supprimé)

---

## 2. mvn test

### Phases exécutées :
- `validate`
- `process-resources`
- `compile`
- `process-test-resources`
- `test-compile`
- `test`

### Fichiers/Dossiers dans target/ :
- `classes/` (fichiers compilés)
- `test-classes/` (tests compilés)
- `maven-status/`
- `generated-sources/`
- `generated-test-sources/`

---

## 3. mvn package

### Phases exécutées :
- `validate`
- `process-resources`
- `compile`
- `process-test-resources`
- `test-compile`
- `test`
- `package`

### Fichiers/Dossiers dans target/ :
- `classes/`
- `test-classes/`
- `maven-status/`
- `generated-sources/`
- `generated-test-sources/`
- `maven-archiver/`
- **`bank-application-1.0-SNAPSHOT.jar`**

---

## 4. mvn verify

### Phases exécutées :
- `validate`
- `process-resources`
- `compile`
- `process-test-resources`
- `test-compile`
- `test`
- `package`
- `post-integration-test`
- `verify`

### Fichiers/Dossiers dans target/ :
- Identiques à `package`
- `bank-application-1.0-SNAPSHOT.jar`

---

## Hypothèse : Différences entre test, package et verify

| Commande | Crée JAR ? | Inclut Tests ? | Vérifications ? |
|----------|-----------|----------------|-----------------|
| `test` | Non | Oui | Non |
| `package` | **Oui** | Oui | Non |
| `verify` | **Oui** | Oui | **Oui** |

**Conclusion** : `verify` est le plus complet pour moi que `package` qui est plus complet que `test`. `verify` il doit être préféré en CI/CD car il inclut les vérifications de qualité.

