# Sulamerica SAS

Esta é uma API REST com Spring Web Security.

Abaixo estão os principais endPoins Utilizados na aplicação:

# Endpoints

1. #Autenticação:- Geração do Token que deve ser utilizado para acessar qualquer requisição no sistema.
>localhost:8080/auth/signin

Exemplo de Body: - Os dados abaixo estão pré-cadastrados no data.sql.
```
{
	"userName":"admin",
	"password":"admin"
}
```

**Obs.:** Para qualquer requisição abaixo será necessário colocar o Bearer Token gerado acima.

2. **Listar Cargos**
>TYPE:GET localhost:8080/cargo/list

2.1. **Adicionar Cargo**
>TYPE:POST localhost:8080/cargo/save

Exemplo de Body:
```
{
	"nome":"Analista de Sistemas"
}
```

2.2. **Update Cargo**
>TYPE:PUT localhost:8080/cargo/update

Exemplo de Body:
```
{
	"id":"4",
	"nome":"Analista de Sistemas 2"
}
```

2.3. **Delete Cargo**
>TYPE:DELETE localhost:8080/cargo/delete

Exemplo de Body:
```
{
	"id":"4"
}
```

3. **Listar Perfis**
>TYPE:GET localhost:8080/perfil/list

3.1. **Adicionar Perfil**
>TYPE:POST localhost:8080/perfil/save

Exemplo de Body:
```
{
	"nome":"Teste Perfil"
}
```

3.2. **Update Perfil**
>TYPE:PUT localhost:8080/perfil/update

Exemplo de Body:
```
{
	"id":"5",
	"nome":"Teste 2"
}
```

3.3. **Delete Perfil**
>TYPE:DELETE localhost:8080/perfil/delete

Exemplo de Body:
```
{
	"id":"4"
}
```

4. **Listar Usuários**
>TYPE:GET localhost:8080/usuario/list

4.1. **Adicionar Usuário**
>TYPE:POST localhost:8080/usuario/save

Exemplo de Body:
```
{
	"nome":"Usuario 3",
	"cpf":"19691926075",
	"sexo":"M",
	"dataNascimento":"1990-02-01",
	"ativo":"true",
	"cargo":{
		"id":"1"
	},
	"perfil":{
		"id":"1"
	},
	"userName":"admin",
	"password":"teste"
}
```

4.2. **Update Usuário**
>TYPE:PUT localhost:8080/usuario/update

Exemplo de Body:
```
{
	"nome":"Usuario 3",
	"cpf":"19691926075",
	"sexo":"M",
	"dataNascimento":"1990-02-01",
	"ativo":"true",
	"cargo":{
		"id":"1"
	},
	"perfil":{
		"id":"1"
	},
	"userName":"admin",
	"password":"teste"
}
```

4.3. **Delete Usuário**
>TYPE:DELETE localhost:8080/usuario/delete

Exemplo de Body:
```
{
	"id":"2"
}
```
