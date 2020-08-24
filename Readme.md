# Background

It is becoming common to undertake scans of the earth’s surface for a variety of purposes, including agricultural and forest management, studies of climate change, and other scientific applications. This also includes so-called bare earth surveys, which map the height of the earth’s surface, stripped of building and trees. In fact, most of the earth’s surface has already been mapped in this way, albeit at a relatively course resolution. The datasets involved can be quite large and their analysis complex, which makes them good candidates for acceleration using parallel computing.

One such analysis is to determine how water flows across the landscape in order to protect against flooding and manage crops. As a precursor to this kind of analysis, in this assignment, you will take a scanned input terrain and find local minima (small-scale basins) where water might accumulate. Figure 1 shows an example of the type of terrain data that you will be analysing.

# Running code

This code comes with a Makefile to allow for easy complition. hence to compile make simply enter 

```

make 

```

Upon compling code, there are two runner classes which can be run i.e.
- [SerialRunner](src/SerialRunner.java)
- [ParallelBasinRunner](src/ParallelBasinRunner.java)

to run programs simply carryout the following in the bin directory

```

java SerialRunner 'Path to input file terrain' 'Path to expected output file'

```

```

java ParallelBasinRunner 'Path to input file terrain' 'Path to expected output file'

```