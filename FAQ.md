#summary JavaFUSE Frequently Asked Questions
#labels Featured

# Error messages #

<br>
<b><code>./javafuse: error while loading shared libraries: libjvm.so: cannot open shared object file: No such file or directory</code></b>

The shared library libjvm.so could not be found. Add the folder containing that file to the environment vairable LD_LIBRARY_PATH. See nullfs_mount.sh for an example of how to tackle this.<br>
<br>
<br>
<b><code>./javafuse: error while loading shared libraries: libjavafuse.so: cannot open shared object file: No such file or directory</code></b>


The shared library libjavafuse.so could not be found. Add the "build" folder to the environment vairable LD_LIBRARY_PATH. See nullfs_mount.sh for an example of how to tackle this.<br>
<br>
<br>
<b><code>WARNING in native method: JNI call made with exception pending</code></b><br>
<code>javafuse: src/native/fsClass.c:35: init_fsClass: Assertion `fuseSt.fsClass != ((void *)0)' failed.</code>


The Java class implementing the file system could not be found. Either an incorrect name for the package/class name was specified, or the class was not compiled. For instance, to compile the NullFS filesystem, do:<br>
<br>
<blockquote>cd fs<br>
make</blockquote>

Note that the "javafuse" executable must be passed the -C flag with a PackageName/ClassName value (see nullfs_mount.sh). Alternatively, the CLASSNAME option can be set in the config file for the filesystem (see fs/nullfs/nullfs.config).<br>
<br>
<br>
<h1>Acknowledgements</h1>

This material is based upon work supported by the National Science Foundation under Grant OCI-0722068. Any opinions, findings, and conclusions or recommendations expressed in this material are those of the author(s) and do not necessarily reflect the views of the National Science Foundation.