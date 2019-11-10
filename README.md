# Sulamerica SAS

Esta é uma API REST com Spring Web Security.

Abaixo estão os principais endPoins Utilizados na aplicação:

# Endpoints

1. #Autenticação:- Geração do Token que deve ser utilizado para acessar qualquer requisição no sistema.
>localhost:8080/auth/signin

**Body:** - Os dados abaixo estão pré-cadastrados no data.sql.
```
{
	"userName":"admin",
	"password":"admin"
}
```

**Obs.:** Para qualquer requisição abaixo será necessário colocar o Bearer Token gerado acima.

2 . **Listar Cargos**
>TYPE:GET localhost:8080/cargo/list

2.1. **Adicionar Cargo**
>TYPE:POST localhost:8080/cargo/save

Body:
```
{
	"nome":"Analista de Sistemas"
}
```

2.2. **Update Cargo**
>TYPE:PUT localhost:8080/cargo/update

Body:
```
{
	"id":"4",
	"nome":"Analista de Sistemas 2"
}
```

2.3. Delete Cargo
>TYPE:DELETE localhost:8080/cargo/delete

Body:
```
{
	"id":"4"
}
```

3. **Listar Perfis**
>TYPE:GET localhost:8080/perfil/list

3.1. **Adicionar Perfil**
>TYPE:POST localhost:8080/perfil/save

Body:
```
{
	"nome":"Teste Perfil"
}
```

3.2. **Update Perfil**
>TYPE:PUT localhost:8080/perfil/update

Body:
```
{
	"id":"5",
	"nome":"Teste 2"
}
```

3.3. Delete Cargo
>TYPE:DELETE localhost:8080/perfil/delete

Body:
```
{
	"id":"4"
}
```
