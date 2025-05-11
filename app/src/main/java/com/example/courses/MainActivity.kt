package com.example.courses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.ui.layout.ContentScale
import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CourseAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        Image(
                            painter = painterResource(id = R.drawable.img),
                            contentDescription = "App Logo",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(170.dp)
                        )
                        CourseListScreen(courses = sampleCourses)
                    }
                }
            }
        }
    }
}

@Composable
fun CourseListScreen(courses: List<Course>) {
    LazyColumn(modifier = Modifier.padding(16.dp)) {

        item {
            Title(modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(16.dp))
        }

        items(courses) { course ->
            CourseCard(course)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun CourseCard(course: Course) {
    var isExpanded by rememberSaveable { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { isExpanded = !isExpanded }
            .animateContentSize(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color(0xFF1E1E2F)
        ),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)
            .animateContentSize()) {
            Text(course.title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Code: ${course.code}")
                Text("Credits: ${course.creditHours}")
            }
            if (isExpanded) {
                Spacer(modifier = Modifier.height(8.dp))
                Text("Description: ${course.description}", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(4.dp))
                Text("Prerequisites: ${course.prerequisites}", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Composable
fun Title(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(12.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Text(
            text = "Academic Courses",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun CourseAppTheme(content: @Composable () -> Unit) {
    val darkColorScheme = darkColorScheme(
        primary = Color(0xFFBB86FC),
        surface = Color(0xFF121212),
        background = Color(0xFF0D47A1),
        onSurface = Color.White
    )
    MaterialTheme(
        colorScheme = darkColorScheme,
        typography = Typography(),
        content = content
    )
}

// Data class and sample data

data class Course(
    val title: String,
    val code: String,
    val creditHours: Int,
    val description: String,
    val prerequisites: String
)

val sampleCourses = listOf(
    Course(
        "Mobile Application Development",
        "CS401",
        3,
        "This course focuses on building modern mobile applications using Android and Kotlin. Topics include user interface design with Jetpack Compose, activity lifecycle, data storage, and network communication. Students will develop fully functional apps and deploy them to emulators or physical devices.",
        "CS301"
    ),
    Course(
        "Data Structures and Algorithms",
        "CS202",
        4,
        "This course introduces core data structures such as arrays, linked lists, stacks, queues, trees, heaps, and graphs. Students learn algorithmic techniques including recursion, searching, and sorting, as well as how to evaluate algorithm efficiency using Big O notation.",
        "CS101"
    ),
    Course(
        "Operating Systems",
        "CS303",
        3,
        "Covers the fundamental concepts of operating systems, including process management, memory management, file systems, synchronization, and system calls. Students explore Linux as a case study and practice shell scripting and process scheduling in labs.",
        "CS202"
    ),
    Course(
        "Artificial Intelligence",
        "CS404",
        3,
        "Introduces the concepts and techniques of artificial intelligence. Topics include intelligent agents, search algorithms, knowledge representation, reasoning, machine learning, and natural language processing. Students will implement basic AI models and algorithms in projects.",
        "CS303"
    ),
    Course(
        "Computer Graphics and Visualization",
        "CS507",
        2,
        "Focuses on the fundamentals of computer graphics including 2D/3D transformations, rendering pipelines, modeling, and animation. Students will use OpenGL or WebGL to develop simple visual applications such as drawing engines, simulations, and basic games.",
        "CS150"
    )
)



@Preview(name = "CourseCard Light Mode", showBackground = true)
@Composable
fun PreviewCourseCardLight() {
    CourseAppTheme {
        CourseCard(
            course = Course(
                "Mobile Development",
                "CS401",
                3,
                "Develop Android apps using Kotlin and Jetpack Compose.",
                "CS301"
            )
        )
    }
}

@Preview(
    name = "CourseCard Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun PreviewCourseCardDark() {
    CourseAppTheme {
        CourseCard(
            course = Course(
                "Mobile Development",
                "CS401",
                3,
                "Develop Android apps using Kotlin and Jetpack Compose.",
                "CS301"
            )
        )
    }
}

@Preview(name = "Course List Light", showBackground = true)
@Composable
fun PreviewCourseListLight() {
    CourseAppTheme {
        CourseListScreen(courses = sampleCourses)
    }
}

@Preview(
    name = "Course List Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun PreviewCourseListDark() {
    CourseAppTheme {
        CourseListScreen(courses = sampleCourses)
    }
}
