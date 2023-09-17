create table produtos(

    id int not null auto_increment,
    nome varchar(100) not null unique,
    descricao varchar(100) not null unique,
    quantidade int not null,
    valor float(10,2) not null,
    
    primary key(id)

);