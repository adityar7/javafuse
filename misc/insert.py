#!/usr/bin/python

"""
    insert.py - Insert text from a file at the top of another file (or all
    files in current folder).

    Usage: python insert.py [INFILE] [OUTFILE] ... [OUTFILE]
    If OUTFILE -all is specified, apply to all files in current folder.
"""

import sys
import os
import shutil

def insert(infile, outfiles):

    if os.path.exists('tmp.txt'):
        print '\nDelete tmp.txt first.\n'
        sys.exit()

    try:
        tmpfile = open('tmp.txt', 'w')
    except IOError:
        print '\nError opening tmp file.\n'
        sys.exit()

    for outfile in outfiles:
        if os.path.isdir(outfile):
            continue
        os.system('cat ' + infile + ' ' + outfile + ' > ' + tmpfile.name)
        shutil.copyfile(tmpfile.name, outfile)

    tmpfile.close()
    os.remove(tmpfile.name)


if __name__ == "__main__":
    if len(sys.argv) <= 2:
        print "\nUsage: python insert.py [INFILE] [OUTFILE] ... " +\
                "[OUTFILE]\nIf OUTFILE -all is specified, apply to all " +\
                " files in current folder.\n"
        sys.exit()

    infile = sys.argv[1]
    outfiles = sys.argv[2:]

    if not os.path.exists(sys.argv[1]):
        print '\nInvalid input file: ' + infile + '\n'
        sys.exit()

    if outfiles[0] == '-all':
        outfiles = os.listdir('.')

    insert(infile, outfiles)
#    insert()
