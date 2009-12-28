# Mounts the NullFS file system, which is part of the nullfs package, on a folder called tmp/
# The -f flag specifies that the FUSE daemon will run in the foreground.
./javafuse -Cnullfs/NullFS -f tmp/
