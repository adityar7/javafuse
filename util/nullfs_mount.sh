# Mounts the NullFS file system, which is part of the nullfs package, on a 
# folder called tmp/

# The -f flag and the big_writes and auto_cache options are FUSE parameters.

./javafuse -f -o big_writes -o auto_cache -Cnullfs/NullFS -Ffs/nullfs/nullfs.config tmp/
