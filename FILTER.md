# Filter Design V2


## Introduction

Filtering is the process of limiting a collection resource by using a per-request dynamic criteria definition.Filtering enables efficient traversal of large collections.
Filtering can also be backed with pagination where each page contains a subset of items found in the complete collection.

To filter in a query, include the parameter q=QueryObject, where QueryObject is a JSON object that represents the custom selection, pagination and sorting to be applied to the resource. 

For example, assume the following resource:

```ruby
https://db2rest.com/actors
```

The following query includes a filter that restricts actors with first_name column to "Robert"

```ruby
https://db2rest.com/actors?q={"first_name":"Robert"}
```

## QueryObject Grammer

**EQUALS operator ($eq)**

(Implicit and explicit equality supported.)

Implicit (Support String and Dates too)

```ruby
https://db2rest.com/actors?q={"year": 1999}
```

Explicit

```ruby
https://db2rest.com/actors?q={"year": {"$eq": 1000} }
```

Strings

```ruby
https://db2rest.com/actors?q={"first_name": {"$eq": "Robert"} }
```

Dates

```ruby
https://db2rest.com/actors?q={"date_of_birth": {"$date": "1981-11-17T08:00:00Z"} }
```


**NOT EQUALS operator ($ne)**

Number

```ruby
{"budget": {"$ne": 1000000}}
```

String

```ruby
{"last_name": {"$ne":"De Niro"}}
```

Dates

```ruby
{"date_of_birth": {"$ne": {"$date":"1981-11-17T08:00:00Z"}}}
```


**LESS THAN operator ($lt)**
(Supports dates and numbers only)

Numbers

```ruby
{"budget": {"$lt": 100000000} }
```

Dates

```ruby
{"date_of_release": {"$lt": {"$date":"1999-12-17T08:00:00Z"}}}
```

**LESS THAN OR EQUALS operator ($lte)**
(Supports dates and numbers only)

Numbers

```ruby
{"budget": {"$lte": 100000000}}
```

Dates

```ruby
{ "date_of_birth": {"$lte": {"$date":"1999-12-17T08:00:00Z"}} }
```

**GREATER THAN operator ($gt)**
(Supports dates and numbers only)

Numbers

```ruby
{ "budget": {"$gt": 10000} }
```

Dates

```ruby
{ "date_of_birth": {"$gt": {"$date":"1999-12-17T08:00:00Z"}} }
```

**GREATER THAN OR EQUALS operator ($gte)**
(Supports dates and numbers only)

Numbers

```ruby
{"budget": {"$gte": 10000}}
```

Dates

```ruby
{"date_of_birth": {"$gte": {"$date":"1999-12-17T08:00:00Z"}} }
```

In string operator ($instr)
(Supports strings only)

{
"ENAME": {"$instr":"MC"}
}


Not in string operator ($ninstr)
(Supports strings only)

{
"ENAME": {"$ninstr":"MC"}
}



#### LIKE operator ($like)
(Supports strings. Eescape character not supported to try to match expressions with _ or % characters.)

{
"ENAME": {"$like":"AX%"}
}


#### BETWEEN operator ($between)
(Supports string, dates, and numbers)

Numbers

{
"SALARY": {"$between": [1000,2000]}
}

Dates

{
"SALARY": {"$between": [{"$date":"1989-12-17T08:00:00Z"},{"$date":"1999-12-17T08:00:00Z"}]}
}

Strings

{
"ENAME": {"$between": ["A","C"]}
}

Null Ranges ($lte equivalent)
(Supported by numbers and dates only)

{
"SALARY": {"$between": [null,2000]}
}

Null Ranges ($gte equivalent)
(Supported by numbers and dates only)

{
"SALARY": {"$between": [1000,null]}
}


#### NULL operator ($null)

{
"ENAME": {"$null": null}
}

#### NOT NULL operator ($notnull)

{
"ENAME": {"$notnull": null}
}
 