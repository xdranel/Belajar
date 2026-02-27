/*
########## MAIN INDEX ##########

// HTML
001. SEMANTIC AND NON-SEMANTIC HTML
002. ORDERED AND UNORDERED LIST
003. FORM, INPUT TYPE, TABLE

// BASIC CSS
004. BOX SIZING
005. DISPLAY
006. TEXT, FONT
007. CENTER DIV PART 1

// INTERMEDIATE CSS
008. BACKGROUND IMAGE, TRANSPARENT, ALPHA VALUES, OPACITY
009. GRADIENTS, SHADOWS
010. COMBINATORS
011. ATTRIBUTE SELECTORS
012. PSEUDO CLASSES
013. TRANSITIONS
014. POSITION
015. PSEUDO ELEMENT
016. CSS VARIABLE (Custom Properties)
017. MAX/MIN WIDTH
018. ANIMATIONS

// RESPONSIVE WEB DESIGN
// FLEXBOX
019. DISPLAY FLEX
020. FLEX-DIRECTION, GAP, SPACE
021. FLEX-WRAP, ALIGN-CONTENT VS ALIGN-ITEMS,
     NESTED FLEXBOX, ROWS AND COLUMNS GAP,
     FLEX-GROW AND FLEX-SHRINK
022. MEDIA QUERIES
## TIPS ##

// GRID
023. COLUMNS, ROWS, ALIGNMENT GRID CELL
    AUTOMATIC COLUMNS/ROWS, GRID AUTO FLOW
024. STRETCH ELEMENT, TEMPLATE-AREA
## TIPS ##

025. has(), not()
026.
027.
028.
029.
030.

###############################
##### 001. SEMANTIC NON-SEMANTIC HTML
dont just use all div/span but use what html feature offer
because not only it is easy to use and understand
but also it is easy to design
and it also have impact on SEO, accesibilty etc.
but use NON-SEMANTIC for something like design inside nav, aside etc


<nav> </nav>
<header> </header>
<section> </section>
<main> </main>
<aside> </aside>
<footer> </footer>

<hr />
###############################
##### 002. ORDERED AND UNORDERED LIST
  <strong>Ordered List</strong>
  <ol>
    <li>First</li>
    <li>Second
      <ol>
        <li>2.1</li>
        <li>2.2</li>
        <li>2.3</li>
        <li>2.4</li>
      </ol>
    </li>
    <li>Third</li>
    <li>Fourth</li>
  </ol>

  <strong>Unordered List</strong>
  <ul>
    <li>First</li>
    <li>Second
      <ul>
        <li>2.1</li>
        <li>2.2</li>
        <li>2.3</li>
        <li>2.4</li>
      </ul>
    </li>
    <li>Third</li>
    <li>Fourth</li>
  </ul>

###############################
##### 003. FORM, INPUT, TABLE
<form>
    <input type="text" placeholder="Input Field">
    <br><br>
    <input type="number" placeholder="Number Field">
    <br><br>
    <input type="checkbox">
    <input type="checkbox">
    <br><br>
    <input type="password" placeholder="Password">
    <br><br>
    <input type="email" placeholder="email">
    <br><br>
    <input type="submit">
    <br><br>
    <input type="reset">
    <br><br>
    <label for="male">Male</label>
    <input id="male" type="radio" name="gender">
    <label for="female">Female</label>
    <input id="female" type="radio" name="gender">
    <br><br>
    <input type="date">
    <br><br>
    <input type="time">
    <br><br>
    <input type="color">
    <br><br>
    <input type="file">
    <br><br>
    <input type="search">
</form>

<table cellspacing="20">
  <tr>
    <td>
      <img src="images/cs2.png" width="300px" alt="counter-strike2-image1">
    </td>
    <td>
      <h1>Counter-Strike 2</h1>
      <p>Release Date: 23/07/2023</p>
      <p>
        See Oficial Website
        <a href="https://www.counter-strike.net">
        </a>
      </p>
    </td>
  </tr>
</table>

<video src="video/1.mp4" controls> </video>

<button title="">Tooltip<button/>

###############################
##### 004. BOX SIZING

When you adding for example padding it will give extra space more than the actual width/height
that already set, in order to set the fixed size you can use BOX SIZING

/// box-sizing ///
width + padding + border = actual width
height + padding + border = actual height

/// box-sizing: content-box; => the value for most elements
/// box-sizing: border-box;
div {
  width: 200px;
  height: 50px;
  margin: 10px;
  background-color: lightblue;
  padding: 20px;
  border: 10px solid black;
}
.contentBox {
  box-sizing: content-box;
}
.borderBox {
  box-sizing: border-box;
}

###############################
##### 005. DISPLAY
display: none;
display: block;
display: inline;
display: inline-block;

vertical margin dont work in inline elements
height and width also not work in inline elements
to use vertical margin use display: block or display: inline-block
or vice versa

difference between inline and inline-block
inline-block: inline elements can have margin and padding

###############################
##### 006. TEXT, FONT

## TEXT ##
text-align => text-align: left/center/right
text-decoration => text-decoration: underline wavy #123456 5px;
text-transform

## FONT ##
font-size
font-family
font-style
font-weight

Fixed Size(Remember This)
px => use it for font-size => default 16px
pt => use it for font-size => default 12pt => use pt for font-size

So if you build only for 1 device use fixed size but if you build for many different device
use Relative size

Relatives Size
1em, 1rm

em font size is relative to the parent element
use it for margin and padding
div {
  font-size: 10pt;
}
p {
  font-size: 2em;
}

rm font size is relative to the root element
combine this with root element by using pt for root, rm for font-size
html {
  font-size: 10pt;
}
h1 {
  font-size: 2rem;
}
p {
  font-size: 1rem;
}

###############################
##### 007. CENTER DIV PART 1
html {
  height: 100%;
}
body {
  align-content: center;
  height: 100%;
  margin: 0;
}
div {
  width: 50%;
  height: 300px;
  background-color: lightblue;
  margin: 0 auto;
  align-content: center;
}
p {
  width: 50%;
  height: 150px;
  background-color: gray;
  margin: 0 auto;
  text-align: center;
  align-content: center;
}

###############################
##### 008. BACKGROUND IMAGE, TRANSPARENT, ALPHA VALUES, OPACITY

// Background Image
background-image: url("../images/adventuretime.jpg");
background-repeat: no-repeat;
background-size: cover/contain;
background-attachment: fixed/scroll;
background-position: center;

// TRANSPARENT, ALPHA, OPACITY
Alpha only effect its background color
Opacity effect its entire element

div {
  width: 300px;
  height: 300px;
  display: inline-block;
  margin-right: 50px;
  border: 5px solid black;
}
.alpha {
  background-color: rgba(200, 0, 0, 0.5);
}
.opacity {
  background-color: blue;
  opacity: 0.5;
}

###############################
##### 009. GRADIENTS, SHADOWS
.linear {
  background-image: linear-gradient(45deg, red 30%, yellow 50%, blue 80%);
}
you can mix between linear and radial
.radial {
  background-image: radial-gradient(red, yellow, blue);
}

// GRADIENTS example
html {
  height: 100%;
}
body {
  background-image: url("../images/galaxies.jpg");
  background-repeat: no-repeat;
  background-size: cover;
  background-attachment: fixed;
  background-position: center;

  align-content: center;
  height: 100%;
  margin: 0;
}
.blackhole {
  width: 300px;
  height: 300px;
  background-image: radial-gradient(black 30%, white 31%, transparent 40%);
  background-size: cover;
  margin: 0 auto;
  align-content: center;
}

// SHADOWS example
box-shadow: 10px 10px 15px 20px grey inset, 5px 5px 5px black;

###############################
##### 010. COMBINATORS

# 1. Children
header>h2 {
  color: blue;
}
header.container>h2 {
  color: green;
}
div>article>p {
  color: cyan;
}

# 2. Descendant
div h2 {
  color: red;
}

# 3. Subsequent Sibling (Only Sibling that have the same parent, it will not effect if sibling before current element)
h2~p {
  font-weight: bold;
  color: blue;
}

# 4. Next Sibling (Only Effect Immediate Sibling)
h2+p {
  font-weight: bold;
  color: blue;
}

###############################
##### 011. ATTRIBUTE SELECTORS
input[type="email"],
input[type="password"],
input[type="submit"] {
  height: 25px;
  width: 150px;
  border-radius: 30px;
  border: 1px solid black;
  padding-left: 10px;
}

###############################
##### 012. PSEUDO CLASSES
Target element based on its state or relationship to another element

** a **
a:hover{}
a:visited{}
a:active{}

** input **
input:focus()
input:invalid()
input:valid()

** input[type=""] **
input[type="checkbox"]:checked {
  outline: 2px solid green;
}
input[type="checkbox"]:checked~span {
  text-decoration: line-through;
  color: #ddd;
}

** child (first, last, nth) **
li:first-child {
  border: 2px solid black;
}
li:last-child {
  border: 2px solid blue;
}
li:nth-child(5) {
  background-color: orange;
}
li:nth-child(even/odd/2n) {
  background-color: lightblue;
}

###############################
##### 013. TRANSITIONS
put transition on the default non hover selector

transition: all 0.5s ease-in-out;

you can also inspect the transition in browser to make custom effect

###############################
#### 014. POSITION

positon: static, relative, absolute, fixed, sticky;
if you assign values other than static, then you can use
properties top,bottom,left,right,z-index

relative : change the position of the element relative to its current position
  position: relative;
  top: 50px;
  left: 50px;

absolute : change the position of the element relative to the nearest positioned ancestor
  .parent {
    padding: 30px;
    width: 500px;
    background-color: black;
    color: white;

    position: relative;
  .child {
    padding: 20px;
    background-color: #0071FF;
    color: white;

    position: absolute;
    top: 0;
    right: 0;

    z-index: 10;
  }

fixed : change the position of the element relative to the viewport/window
you can use percentage values to center it but the top left will centered(origin)
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);

sticky : change the position of the element relative to the nearest scrolling ancestor
behave just like fixed but it will keep its position even if the element is scrolled
  position: sticky;
  top: 50px;


###############################
##### 015. PSUEDO ELEMENT
Target specific part of the element

** Basic **
p::first-letter {
  font-weight: bold;
  font-size: 40px;
}
p::first-line {
  background-color: gray;
}
input[type="text"]::placeholder {
  font-weight: bold;
  color: red;
}

** ::before & ::after **

h1 {
  position: relative;
  width: max-content;
}
h1::before/after {
  content: "";
  background: linear-gradient(to right, red, blue);
  height: 4px;
  width: 100%;
  position: absolute;
  bottom: 0;
  left: 0;
  z-index: 10;
  transition: 200ms;
}
h1:hover::after {
  width: 0;
}

###############################
#### 016. CSS VARIABLE (Custom Properties)

variable created in the root
:root {
  --primary-color: #007bff;
}
button {
  height: 30px;
  width: 100px;
  background-color: var(--primary-color);
}

###############################
##### 017. MAX/MIN WIDTH
responsive design

width: 1000px;
max/min-width: 90%;
max/min-height 400px;

width: min/max(1000px, 90%)

in many cases max/min-height not recomended, but
if it necesessary, then use overflow

overflow: hidden;
overflow: scroll;
overflow: auto;

###############################
##### 018. ANIMATIONS

Before assign an animation to element, you need to create it using @keyframes

.element{
  animation-duration: 3s;
  animation-name: spin;
  animation-timing-function: ease-in-out;
  animation-delay: 1s;
  animation-iteration-count: infinite;
  animation-direction: normal;
  animation-fill-mode: forwards;
  animation-play-state: running;

  animation: 32 spin ease-in-out 1s infinite normal forwards running;
}

.element:hover {
  animation-play-state: running;
}

@keyframes spin {
  0% {}

  50% {
    scale: 2;
    transform: rotate(0);
    border-radius: 0;
  }

  100% {
    transform: rotate(360deg);
    border-radius: 50%;
    scale: 2;
  }
}

##### RESPONSIVE WEB DESIGN #####
see MDN docs for more value

************** FLEXBOX **************

###############################
##### 019. DISPLAY FLEX

// flex //
to align the element with flexbox you need to change the parent element 
display: flex

flex-container had 2 axis main and cross
main-axis: horizontal
cross-axis: vertical

to position the element inside the flex-container
justify-content: horizontal
align-items: vertical

justify-content: flex-start/end/center;
align-items: flex-start/end/center;


###############################
##### 020. FLEX-DIRECTION, GAP, SPACE

// flex-direction //
flex-direction: row/row-reverse/column/column-reverse;
row = horizontal
column = vertical

// gap //
gap: 20px;
giving gap between elements

// space //
space-evenly = distribute the space evenly
space-between = distribute the space between elements
space-around = distribute the space around elements
combine space and gap with justify-content and align-items

###############################
##### 021. FLEX-WRAP, ALIGN-CONTENT VS ALIGN-ITEMS,
           NESTED FLEXBOX, ROWS AND COLUMNS GAP,
           FLEX-GROW AND FLEX-SHRINK

// flex-wrap //
flex-wrap: nowrap;
flex-wrap: wrap;
flex-wrap: wrap-reverse;
combine flex-wrap and align-content with justify-content and align-items

// ALIGN-ITEMS and ALIGN-CONTENT //
align-items: control alignment of items on the cross axis of every flexbox
line individually
align-content: control alignment of all the lines together

example :
div {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  align-items: center;
  align-content: center;
}

// ROW AND COLUMNS GAP //
row-gap: 20px;
column-gap: 20px;

// FLEX-GROW AND FLEX-SHRINK //
flex-grow: 0/1;
flex-shrink: 0/1;

** Combining Flexbox With Flex-Grow and Flex-Shrink **
by combining flex-grow and flex-shrink you can control the size of the element
for example if you make todoApp where the checkbox not grow/shrink
flex-shrink/grow: 0; but on other targeted element flex-shrink/grow: 1;

most of the time you will use flex-grow

body {
  min-height: 800px;
  border: 8px solid black;
  display: flex;
  gap: 20px;
}
#boxid {
  flex-shrink/grow: 5;
}
.box {
  flex-shrink/grow: 1;
  display: flex;
}

** Combining Flexbox With Min/Max Width and Height **
example :
.box {
  flex-grow: 1;
  max-width: 300px;
  display: flex;
}
this will make the box grow to 300px

.box {
  flex-shrink: 1;
  max-width: 100px;
  display: flex;
}
this will make the box shrink to 100px after reaching 100px it will overflow

to overcome the overflow problem you can use
@media(max-width: 575px) {
  body {
    flex-wrap: wrap;
  }
}

###############################
##### 022. MEDIA QUERIES
media queries allow you to create responsive web design
@media(min/max-width: 575px) {
  body {
    flex-wrap: wrap;
    justify-content: center;
    align-items: center;
  }
}

### TIPS ###
align-self: ;
for example
align-self: flex-start/end/center/stretch;
its use for aligning the element with the cross axis

###
body {
  align-items: flex-start;
  justify-content: flex-end;
}
#box-1{
  margin-right: auto
}
###

###############################
##### 023. COLUMNS, ROWS, ALIGNMENT GRID CELL
      AUTOMATIC COLUMNS/ROWS GRID, GRID AUTO FLOW

// GRID COLUMNS AND ROWS //
grid-template-columns: 1fr 1fr;
grid-template-rows: 300px 300px;
fr : fraction

usually you dont need to set the rows because it will adjust automatically with columns
mix it with gap:
grid-template-columns: 1fr 1fr;
grid-template-rows: 300px 300px;
gap: 10px;

// ALIGNMENT GRID CELL //
use justify/align-self: center;
to center the element inside the grid

usually you dont need to specify width/height if you want to fill the space grid cell.
normally you don't specify it because you need to remember every each main and cross axis each cell and also
you don need justify/align-self you can just let the display grid and on the grid-template control it

// AUTOMATIC COLUMNS/ROWS GRID //
grid-auto-columns: 1fr 1fr;
grid-auto-rows: 300px;
by using ^^^^^ is why you dont need to use grid-template-rows because it will adjust automatically with columns

// GRID AUTO FLOW //
grid-auto-flow: row/column;
row = horizontal
column = vertical

Example Horizontal Scrolling Like Netflix :
./exercise/netflix_card_like

*** SUMMARY ***
#1. if you know exactly how many columns you want to use, use grid-template-columns/rows
#2, if you dont know exactly how many columns you want to use, use grid-auto-columns/rows

you can combine it with 1fr, justify/align-self, gap

###############################
##### 024. STRETCH ELEMENT, TEMPLATE-AREA

// STRETCH ELEMENT //
grid-column-start: 2;
grid-column-end: 4;
grid-column: 2 / 4;

grid-row-start: 1;
grid-row-end: 3;
grid-row: 1 / 3;

Example : bento grid
./exercise/bento_grid

// TEMPLATE AREA //
grid-template-areas: "";

** HTML SHOULD HAVE THIS **
<div style="grid-area: box-1;" class="box">1</div>

Example : Grid Areas
./exercise/grid_area

## TIPS ##

you can use repeat()
grid-template-columns/rows: repeat(3, 1fr);
first value = number of columns/rows
second value = size of each column/rows
dont use fixed values for the size of the column/rows

you can alos use autofit with the fix second values
grid-template-columns: repeat(auto-fit, 300px);
but there will be some space left

if you try to give second values 1fr
grid-template-columns: repeat(auto-fit, 1fr);
it will take entire space than what you desire

to solve this problem, use minmax
grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));




















































*/
