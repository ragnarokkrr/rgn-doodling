# spring bindings test




## calls
```bash
 ragna.springbindings.PersonRestController.getWithRequestParam

$ http localhost:8080/getWithRequestParam personDTO=='{"firstName":"Dan","secondName":"Newton","profession":"Java Developer","salary":1234.5,"dateOfBirth":"06/01/1994"}'

ragna.springbindings.PersonRestController.getWithRequestParamList

$ http localhost:8080/getWithRequestParamList personDTOList=='[{"firstName":"Dan","secondName":"Newton","profession":"Java Developer","salary":1234.5,"dateOfBirth":"06/01/1994"}]'

ragna.springbindings.PersonRestController.getWithRequestParamList(ragna.springbindings.PersonDto[])

$  http localhost:8080/getWithRequestParamListBind personDTOList=='[{"firstName":"Dan","secondName":"Newton","profession":"Java Developer","salary":1234.5,"dateOfBirth":"06/01/1994"}]'

$ http localhost:8080/getWithRequestArrayZip name=="John" salary=="45.6" filterKey=="k1" filterValue=="v1" filterKey=="k2" filterValue=="v2"
```


## Reference

* [Passing Data Transfer Objects with GET in Spring Boot](https://lankydanblog.com/2017/03/11/passing-data-transfer-objects-with-get-in-spring-boot/)
* [Enums as Request Parameters in Spring Boot Rest(http://www.devglan.com/spring-boot/enums-as-request-parameters-in-spring-boot-rest)
