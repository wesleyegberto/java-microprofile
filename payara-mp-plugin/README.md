# Build
mvn clean package && docker build -t com.github.wesleyegberto.microprofile/payara-mp-plugin .

# RUN

docker rm -f payara-mp-plugin || true && docker run -d -p 8080:8080 -p 4848:4848 --name payara-mp-plugin com.github.wesleyegberto.microprofile/payara-mp-plugin 