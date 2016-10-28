package main;

public class TestTokenizer
{

  /**
   * @param args
   */
  public static void main(String[] args)
  {
    Tokenizer tokenizer = new Tokenizer();
    tokenizer.add("([\"])(?:(?=(\\\\?))\\2.)*?\\1", 2);
    tokenizer.add("(['])(?:(?=(\\\\?))\\2.)*?\\1", 1);
    tokenizer.add("\\(", 3);
    tokenizer.add("\\)", 4);
    tokenizer.add("=", 5);
    tokenizer.add("(?i)(and|or)", 6);

    try
    {
      tokenizer.tokenize("'FieldA' = \"abcde\" AND 'FieldB' = \"abcde\" AND 'FieldC' = \"abcde\"");

      for (Tokenizer.Token tok : tokenizer.getTokens())
      {
        System.out.println("" + tok.token + " " + tok.sequence);
      }

      Parser parser = new Parser();
      parser.parse(tokenizer.getTokens());
    }
    catch (ParserException e)
    {
      System.out.println(e.getMessage());
    }

  }
}
