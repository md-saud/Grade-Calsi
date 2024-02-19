import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText[] subjectEditTexts;
    EditText[] marksEditTexts;
    Button calculateButton;
    TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        subjectEditTexts = new EditText[]{
            findViewById(R.id.editTextSubject1),
            findViewById(R.id.editTextSubject2),
            findViewById(R.id.editTextSubject3)
            // Add more EditTexts as needed
        };

        marksEditTexts = new EditText[]{
            findViewById(R.id.editTextMarks1),
            findViewById(R.id.editTextMarks2),
            findViewById(R.id.editTextMarks3)
            // Add more EditTexts as needed
        };

        calculateButton = findViewById(R.id.calculateButton);
        resultTextView = findViewById(R.id.resultTextView);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateResult();
            }
        });
    }

    private void calculateResult() {
        int totalMarks = 0;
        int totalSubjects = subjectEditTexts.length;
        double totalGPA = 0;

        StringBuilder resultBuilder = new StringBuilder();

        for (int i = 0; i < totalSubjects; i++) {
            String subject = subjectEditTexts[i].getText().toString();
            int marks = Integer.parseInt(marksEditTexts[i].getText().toString());
            totalMarks += marks;

            double subjectGPA = calculateGPA(marks);
            totalGPA += subjectGPA;

            resultBuilder.append(subject).append(": ").append(getGrade(subjectGPA)).append("\n");
        }

        double averagePercentage = (double) totalMarks / totalSubjects;
        resultBuilder.append("\nTotal Marks: ").append(totalMarks).append("\n");
        resultBuilder.append("Average Percentage: ").append(averagePercentage).append("%\n");
        resultBuilder.append("Total GPA: ").append(totalGPA).append("\n");

        resultTextView.setText(resultBuilder.toString());
    }

    private double calculateGPA(int marks) {
        if (marks >= 90) {
            return 4.0;
        } else if (marks >= 80) {
            return 3.5;
        } else if (marks >= 70) {
            return 3.0;
        } else if (marks >= 60) {
            return 2.5;
        } else {
            return 2.0;
        }
    }

    private String getGrade(double gpa) {
        if (gpa >= 4.0) {
            return "A";
        } else if (gpa >= 3.5) {
            return "B";
        } else if (gpa >= 3.0) {
            return "C";
        } else if (gpa >= 2.5) {
            return "D";
        } else {
            return "F";
        }
    }
}
