# pwncheck

Check a password against the Pwned Passwords API. This allows you to check a password for a history of use in breaches, using the k-Anonimity feature to safely avoid sharing that password.

## Usage

```
$ lein run password
The password has been seen 3303003 times
$ lein run passwordergergweg
No passwords were found
```

## More information

Does NOT send passwords off your own machine. More information: https://www.troyhunt.com/ive-just-launched-pwned-passwords-version-2/

## License

Copyright © 2018 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
