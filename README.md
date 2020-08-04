# recruitment-min-triangle-path

Solution
======================

Program can be run in couple ways. Straight from the sbt as follows:
```
> cat example-input.txt | sbt run 
```

or with provided bash helper script which silences sbt output

```
> cat example-input.txt | ./min-triangle.sh
```

Note that there is "warm up" overhead for running sbt and compiling code. For benchmark purposes program should be packaged:
```
> sbt assembly
> cat example-input.txt |  java -jar ./target/scala-2.13/recruitment-min-triangle-path-assembly-0.1.jar
```


Minimum Triangle Paths
======================

Task description
----------

Consider the following triangle of non-negative integers:

<pre>
            7
         6     3
      3     8     5
   11    2     10    9
</pre>

A path through the triangle is a sequence of adjacent nodes, one from each row, starting from the top. So, for instance, 7 + 6 + 3 + 11 is a path down the left hand edge of the triangle.
A minimal path is one where the sum of the values in its nodes is no greater than for any other path through the triangle. In this case, 7 + 6 + 3 + 2 = 18 is a minimal path.
We can store the triangle in a text file with each row on a separate line, and spaces between the numbers.
Thus the triangle above would be stored in text format as:

<pre>
7
3 6
3 8 5
11 2 10 9
</pre>
