# recruitment-min-triangle-path

Solution
======================

Simple recursive bottom-to-top solution.

Program can be run in couple ways. Straight from the sbt as follows:
```
> cat example-input.txt | sbt run 
```

or with provided bash helper script which silences sbt output

```
> cat example-input.txt | ./min-triangle.sh
```

Errors in the input are properly reported:

```
> cat << EOF | ./min-triangle.sh
7
6 3
3 a 5
11 b 10 0.1
EOF
Error: Errors in the line 2 '3 a 5':
	There's invalid input. 'a' is not a integer!

Errors in the line 3 '11 b 10 0.1':
	There's invalid input. 'b' is not a integer!
	There's invalid input. '0.1' is not a integer!

```
or 

```
> cat << EOF | ./min-triangle.sh                                                                                                                                 <<<
7
6 3 2
3 1 5
EOF
Error: Every row of triangle definition needs to have one more element than previous one.
```

Note that there is "warm up" overhead for running sbt and compiling code. For benchmark purposes program should be packaged:
```
> sbt assembly
> cat example-input.txt | java -jar ./target/scala-2.13/recruitment-min-triangle-path-assembly-0.1.jar
```

Possible improvements
======================

I guess one could think about parallelism. Even simple parallel collections. But I'm not sure it will be worth all the context switching.

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
