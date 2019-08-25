package ru.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTests {

    @Test
    public void testCommits() throws IOException {
        Github github = new RtGithub("63cfff5019d480d4fb7806dc5a213b764e093c18");
        RepoCommits commits = github.repos()
                .get(new Coordinates.Simple("AlenaProhorova", "java_programming_course"))
                .commits();
        for (RepoCommit commit: commits.iterate(new ImmutableMap.Builder<String, String>().build())){
            System.out.println(new RepoCommit.Smart(commit).message());
        }
    }
}
