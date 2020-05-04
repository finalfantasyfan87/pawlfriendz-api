#hnmm cool test datatool
# https://faker.readthedocs.io/en/
#also has a java faker class..cool: https://www.baeldung.com/java-faker

from faker import Faker
fake = Faker()
data = {
    "firstName": fake.first_name(),
    "lastName": fake.last_name(),
    "email": fake.free_email(),
    "password": fake.password(length=10),
    "username": fake.simple_profile()["username"],
    "phoneNumber": fake.numerify(text='###-###-####')
}

print(str(data).replace("'", '"'))
