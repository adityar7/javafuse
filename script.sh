fusermount -u tmp/
make
./javafuse -f -o big_writes -o auto_cache -Cnullfs/NullFS -Ffs/nullfs/nullfs.config tmp/
