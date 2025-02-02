/*
 * Copyright (c) 2009-2024 Yegor Bugayenko
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met: 1) Redistributions of source code must retain the above
 * copyright notice, this list of conditions and the following
 * disclaimer. 2) Redistributions in binary form must reproduce the above
 * copyright notice, this list of conditions and the following
 * disclaimer in the documentation and/or other materials provided
 * with the distribution. 3) Neither the name of the rultor.com nor
 * the names of its contributors may be used to endorse or promote
 * products derived from this software without specific prior written
 * permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT
 * NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL
 * THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.rultor.agents.github;

import com.jcabi.aspects.Immutable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * GitHub URL for Issue.
 *
 * @since 2.0
 */
@Immutable
@ToString
@EqualsAndHashCode(of = "url")
@SuppressWarnings("PMD.ConstructorShouldDoInitialization")
final class IssueUrl {

    /**
     * Pattern for issue Url.
     */
    private final Pattern correct =
        Pattern.compile(".*/(?:issues|pull)/(\\d+)(?:/|$).*");

    /**
     * Url.
     */
    private final String url;

    /**
     * Ctor.
     * @param url Issue url.
     */
    @SuppressWarnings("PMD.ConstructorOnlyInitializesOrCallOtherConstructors")
    IssueUrl(final String url) {
        if (url == null || url.isEmpty()) {
            throw new IllegalArgumentException("URL should not be empty");
        }
        this.url = url;
    }

    /**
     * Get issue id from url.
     * @return Issue id
     * @checkstyle MethodNameCheck (10 lines)
     */
    @SuppressWarnings("PMD.ShortMethodName")
    public int id() {
        final Matcher matcher = this.correct.matcher(this.url);
        if (matcher.matches()) {
            return Integer.parseInt(matcher.group(1));
        }
        throw new IllegalStateException(
            String.format("Url %s is not valid issue url", this.url)
        );
    }

    /**
     * Check if url is a valid url for Issue.
     * @return True if valid
     */
    public boolean valid() {
        return this.correct.matcher(this.url).matches();
    }
}
