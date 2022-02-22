### Create keyfiles for MongoDB nodes to authenticate themselves

For this we need to create a keyfile.

In Linux, the following are the commands:
```bash
openssl rand -base64 700 > file.key
chmod 400 file.key
```
