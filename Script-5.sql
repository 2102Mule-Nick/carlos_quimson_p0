
-- First batch of codes to create tables and schema
create schema project0;

select * from users;

drop table users;

create table users (
	user_id serial primary key,
	username text not null,
	first_name text not null,
	last_name text not null,
	passcode text not null);
	
create table accounts(
	account_id serial primary key,
	account_type text not null,
	account_owner int references users (user_id),
	balance decimal not null);
	

create table transactions(
	transaction_id serial primary key,
	user_id int references users (user_id),
	account_id int references accounts (account_id),
	previous_balance decimal,
	new_balance decimal,
	change_amount decimal,
	transaction_type text);
	

insert into users(username, first_name, last_name, passcode)
	values ('carlosq', 'carlos', 'test', '1234');
	
select * from users;

select * from accounts;

select * from transactions;

insert into accounts(account_type, balance, account_owner)
	values('checking', 50, (select user_id from users where username = 'carlosq'));
	
insert into accounts(account_type, balance, account_owner)
	values('saving', 500, (select user_id from users where username = 'carlosq'));
	
--stored procedures functions and triggers
-- this function is currently being called from jdbc whenever a transaction is created
-- this is also the function i'd like to convert to a trigger function
create or replace procedure calculateChangeAmount(trans_id int) 
	as $$
declare 
	amount decimal;
begin 
	if (select transaction_type from transactions where transaction_id = trans_id) = 'deposit' then
		amount := (select new_balance from transactions where transaction_id = trans_id ) - (select previous_balance from transactions where transaction_id = trans_id );
	else 
		amount := (select previous_balance from transactions where transaction_id = trans_id ) - (select new_balance from transactions where transaction_id = trans_id );
	end if;
	
	update transactions set change_amount = amount where transaction_id = trans_id;

end $$ language plpgsql;

call calculateChangeAmount(6);

select * from transactions order by transaction_id desc;
select * from accounts;
select count(*) from transactions;
select count(*) from accounts;

select * from transactions, accounts;

drop trigger updateChange on transactions;
drop function updatechange;

-- this is the function that works but it returns a negative value
-- how can I have comparisons inside the function?
create or replace function updatechange()
	returns trigger as $updateChange$
begin 
	 
	update transactions set change_amount = (new_balance - previous_balance);
	
	return null;
end $updateChange$ language plpgsql;

-- trigger function is working but it does the function for every row of the table
-- how do I do it for only just the very last row?
create trigger updateChange
	after insert on transactions
	for each statement
	execute procedure updateChange();
	

-- For testing

create schema project0_test;

create table users (
	user_id serial primary key,
	username text not null,
	first_name text not null,
	last_name text not null,
	passcode text not null);
	
create table transactions(
	transaction_id serial primary key,
	user_id int references users (user_id),
	account_id int references accounts (account_id),
	previous_balance decimal,
	new_balance decimal,
	change_amount decimal,
	transaction_type text);
	
create table accounts(
	account_id serial primary key,
	account_type text not null,
	account_owner int references users (user_id),
	balance decimal not null);
	
delete from transactions;