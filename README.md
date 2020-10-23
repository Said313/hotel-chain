run# Hotel Chain App

This is Hotel Chain App.

Front-end part was build using react, react router, axios.

Back-end part was build using Java EE(JAX-RS).

Database part was build using MySQL.

## How to launch production server

### Front-end

First, go to folder "client".

Install node modules:
```bash
npm install
```

Then, create production files:
```bash
npm run build
```

After that, install serve:
```bash
npm install -g serve
```

Launch production server in [http://localhost:5000](http://localhost:5000):
```bash
serve -s build
```

### Back-end
Go to folder "backend".

This is JAX-RS app. You can deploy it with Tomcat(preferred 9.0v). Path should be [http://localhost:8080/backend_war_exploded/](http://localhost:8080/backend_war_exploded/)

This app works as API(Do not have Front-end part) but can handle requests from another server.