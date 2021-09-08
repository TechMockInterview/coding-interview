import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class LetterCombinations
{
    private List<String> combinations = new ArrayList<>();
    private Map<Character, String> letters = Map.of(
            '2', "abc", '3', "def", '4', "ghi", '5', "jkl",
            '6', "mno", '7', "pqrs", '8', "tuv", '9', "wxyz");
    private String phoneDigits;

    public List<String> letterCombinations(String digits)
    {

        if (digits.length() == 0) // If we don't have any digits -> the answer is empty
        {
            return combinations;
        }
        phoneDigits = digits;
        backtrack(0, new StringBuilder()); // Generate all the answers
        return combinations;
    }

    private void backtrack(int index, StringBuilder path) // Generate all the answers recursively
    {

        if (path.length() == phoneDigits.length()) // We found a combination -> adding to combinations
        {
            combinations.add(path.toString());
            return;
        }


        String possibleLetters = letters.get(phoneDigits.charAt(index));

        // Search all possible characters for current digit
        for (char letter: possibleLetters.toCharArray())
        {
            path.append(letter); // Adding the letter to our path
            backtrack(index + 1, path); // Find all possible combination with this letter
            path.deleteCharAt(path.length() - 1); // Deleting the last character of path
        }
    }
}